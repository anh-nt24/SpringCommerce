import MainLayout from "../../layouts/MainLayout";
import {
    MDBBtn,
    MDBCard,
    MDBCardBody,
    MDBCardImage,
    MDBCol,
    MDBContainer,
    MDBRow,
} from "mdb-react-ui-kit";
import React from "react";
import { Link } from "react-router-dom";
import { useEffect } from "react";
import { GetAllCartItems } from "../../api/CartApi";
import { useCookies } from "react-cookie";
import { GetProductDetails } from "../../api/ProductApi";
import { useState } from "react";

const apiUrl = process.env.REACT_APP_API_URL;

const Cart = () => {
    const [cookies] = useCookies(['user', 'id', 'token']);
    const { name, id, token } = cookies;
    const [cartItemsData, setCartItemsData] = useState([]);
    useEffect(() => {
        document.title = 'Cart List';
        const fetchData = async () => {
            const cartItems = await GetAllCartItems(id);
            cartItems.forEach(async (item) => {
                const productId = item.productId;
                const response = await GetProductDetails(productId);
                const product = response.data

                // get product image
                const img_response = await fetch(apiUrl + product.imageUrl);
                if (img_response.ok) {
                    product.imageUrl = URL.createObjectURL(await img_response.blob());
                    product.isLoaded = true;
                } else {
                    console.error(`Failed to fetch image. Status: ${img_response.status}`);
                }
                cartItemsData.push(
                    {
                                'id': productId,
                                'image': product.imageUrl || '',
                                'name': product.name,
                                'quantity': item.quantity,
                                'price': product.price,
                                'total': product.price * item.quantity
                            }
                )
            });
        }
        fetchData()
        console.log(cartItemsData)
    }, []);
    return (
        <MainLayout className='Homepage' page="cart">
            {() => (
                <section className="cart-page" style={{minHeight: "560px"}}>
                    <MDBContainer className="h-100">
                        <MDBRow className="justify-content-center align-items-center h-100">
                            <MDBCol>
                                <p>
                                    <span className="h2">Shopping Cart </span>
                                    <span className="h4">({Array.from(cartItemsData).length} item in your cart)</span>
                                </p>

                                {cartItemsData && Array.from(cartItemsData).length > 0 ? (
                                    <div>
                                        {cartItemsData.map((item, index) => (
                                            <MDBCard className="mb-4">
                                                <MDBCardBody className="p-4">
                                                    <MDBRow className="align-items-center">
                                                        <MDBCol md="2">
                                                            <MDBCardImage
                                                                fluid
                                                                src={item.image}
                                                                alt="Generic placeholder image"
                                                            />
                                                        </MDBCol>
                                                        <MDBCol md="4" className="d-flex justify-content-left">
                                                            <div>
                                                                <p className="small text-muted mb-4 pb-2">Name</p>
                                                                <span className="lead fw-normal mb-0 product-name">{item.name}</span>
                                                            </div>
                                                        </MDBCol>
                                                        
                                                        <MDBCol md="2" className="d-flex justify-content-center">
                                                            <div>
                                                                <p className="small text-muted mb-4 pb-2">Quantity</p>
                                                                <p className="lead fw-normal text-center mb-0">{item.quantity}</p>
                                                            </div>
                                                        </MDBCol>
                                                        <MDBCol md="2" className="d-flex justify-content-center">
                                                            <div>
                                                                <p className="small text-muted mb-4 pb-2">Price</p>
                                                                <p className="lead fw-normal mb-0">$ {item.price}</p>
                                                            </div>
                                                        </MDBCol>
                                                        <MDBCol md="2" className="d-flex justify-content-center">
                                                            <div>
                                                                <p className="small text-muted mb-4 pb-2">Total</p>
                                                                <p className="lead fw-normal mb-0">$ {item.total}</p>
                                                            </div>
                                                        </MDBCol>
                                                    </MDBRow>
                                                </MDBCardBody>
                                            </MDBCard>
                                        ))}
                                    </div>
                                    ) : (<></>)}


                                {cartItemsData && Array.from(cartItemsData).length > 0 && (
                                    <MDBCard className="mb-5">
                                        <MDBCardBody className="p-4">
                                            <div className="float-end">
                                                <p className="mb-0 me-5 d-flex align-items-center">
                                                    <span className="small text-muted me-2">Order total:</span>
                                                    <span className="lead fw-normal">$ {cartItemsData.reduce((sum, item) => sum + item.total, 0)}</span>
                                                </p>
                                            </div>
                                        </MDBCardBody>
                                    </MDBCard>
                                )}
                                

                                <div className="d-flex justify-content-end">
                                    <MDBBtn color="light" size="lg" className="me-2">
                                        <Link to="/" style={{
                                            color: "#000",
                                            textDecoration: "none"
                                        }}>
                                            Continue shopping
                                        </Link>
                                    </MDBBtn>
                                </div>
                            </MDBCol>
                        </MDBRow>
                    </MDBContainer>
                </section>
            )}
        </MainLayout>
    );
};




export default Cart;