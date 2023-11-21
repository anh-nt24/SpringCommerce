import React, { useState } from 'react'
import MainLayout from "../../layouts/MainLayout";
import { Container, Row } from 'react-bootstrap';
import GalleryImage from '../../components/GalleryImage'
import ProductDetails from '../../components/ProductDetails'
import { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { GetProductDetails } from '../../api/ProductApi';

const apiUrl = process.env.REACT_APP_API_URL;

const Detail = () => {
    const { productId } = useParams();
    const [productDetails, setProductDetails] = useState(null);

    useEffect(() => {
        document.title = 'Product Detail';

        const fetchData = async () => {
            const response = await GetProductDetails(productId);
            if (response.status == 200) {
                const product = response.data;
                const img_response = await fetch(apiUrl + product.imageUrl);
                if (img_response.ok) {
                    product.imageUrl = URL.createObjectURL(await img_response.blob());
                    product.isLoaded = true;
                } else {
                    console.log('Error on loading image. Status: ', img_response.status);
                }
                setProductDetails(product);
            }
        }

        fetchData();
    }, []);
    return (
        <MainLayout className='Homepage'>
            <Container className="pt-4" style={{minHeight: "500px"}}>
                <Row>
                {productDetails && (
                    <>
                        <GalleryImage imageUrl={productDetails.imageUrl} />
                        <ProductDetails data={productDetails} />
                    </>
                )}
                </Row>
            </Container>
            {/* <AddToCartForm /> */}
        </MainLayout>
    );
};


export default Detail;