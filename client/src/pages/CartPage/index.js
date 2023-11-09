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

const Cart = () => {
    useEffect(() => {
        document.title = 'Cart List';
    }, []);
    return (
        <MainLayout className='Homepage' page="cart">
            <section className="vh-100 cart-page">
                <MDBContainer className="h-100">
                    <MDBRow className="justify-content-center align-items-center h-100">
                        <MDBCol>
                            <p>
                                <span className="h2">Shopping Cart </span>
                                <span className="h4">(1 item in your cart)</span>
                            </p>

                            <MDBCard className="mb-4">
                                <MDBCardBody className="p-4">
                                    <MDBRow className="align-items-center">
                                        <MDBCol md="2">
                                            <MDBCardImage
                                                fluid
                                                src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/E-commerce/Products/1.webp"
                                                alt="Generic placeholder image"
                                            />
                                        </MDBCol>
                                        <MDBCol md="4" className="d-flex justify-content-left">
                                            <div>
                                                <p className="small text-muted mb-4 pb-2">Name</p>
                                                <span className="lead fw-normal mb-0 product-name">iPad Air kkkkkkkkkkkk kkkkkkkkkkkkkkkkkk kkkkkkkkkkkkkkkkkkkkk kkkkkkkkkkkkkkkkk</span>
                                            </div>
                                        </MDBCol>
                                        
                                        <MDBCol md="2" className="d-flex justify-content-center">
                                            <div>
                                                <p className="small text-muted mb-4 pb-2">Quantity</p>
                                                <p className="lead fw-normal text-center mb-0">1</p>
                                            </div>
                                        </MDBCol>
                                        <MDBCol md="2" className="d-flex justify-content-center">
                                            <div>
                                                <p className="small text-muted mb-4 pb-2">Price</p>
                                                <p className="lead fw-normal mb-0">$799</p>
                                            </div>
                                        </MDBCol>
                                        <MDBCol md="2" className="d-flex justify-content-center">
                                            <div>
                                                <p className="small text-muted mb-4 pb-2">Total</p>
                                                <p className="lead fw-normal mb-0">$799</p>
                                            </div>
                                        </MDBCol>
                                    </MDBRow>
                                </MDBCardBody>
                            </MDBCard>

                            <MDBCard className="mb-5">
                                <MDBCardBody className="p-4">
                                    <div className="float-end">
                                        <p className="mb-0 me-5 d-flex align-items-center">
                                            <span className="small text-muted me-2">Order total:</span>
                                            <span className="lead fw-normal">$799</span>
                                        </p>
                                    </div>
                                </MDBCardBody>
                            </MDBCard>

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
        </MainLayout>
    );
};




export default Cart;