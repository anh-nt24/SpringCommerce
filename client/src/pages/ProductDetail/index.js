import React from 'react'
import MainLayout from "../../layouts/MainLayout";
import { Container, Row } from 'react-bootstrap';
import GalleryImage from '../../components/GalleryImage'
import ProductDetails from '../../components/ProductDetails'
import { useEffect } from 'react';

const Detail = () => {
    useEffect(() => {
        document.title = 'Product Detail';
    }, []);
    return (
        <MainLayout className='Homepage'>
            <Container className="pt-4" style={{minHeight: "500px"}}>
                <Row>
                    <GalleryImage />
                    <ProductDetails />
                </Row>
            </Container>
            {/* <AddToCartForm /> */}
        </MainLayout>
    );
};


export default Detail;