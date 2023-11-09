import React from 'react'
import Container from 'react-bootstrap/esm/Container';
import Row from 'react-bootstrap/esm/Row';
import { useState } from 'react';
import ProductCard from '../ProductCard';
import { useEffect } from 'react';
import Col from 'react-bootstrap/esm/Col';
import FilterPanel from '../Filter';
// import { GetNearestUsers } from '../../../api/ActivityApi';

const apiUrl = process.env.REACT_APP_API_URL;

const Content = () => {
    const [isSearch, setSearch] = useState(false);
    const [products, setProducts] = useState(null);

    // useEffect(() => {
    //     const getAllProducts = async () => {
    //         const response = await GetNearestUsers(token.token);
    //         if (response.status === 200) {
    //             var data = response.response;
    //             const promises = [];
    //             for (const product of data) {
    //                 for (let i = 0; i < product.image.length; i++) {
    //                     const img_response = await fetch(apiUrl + product.image[i]);
    //                     product.image[i] = URL.createObjectURL(await img_response.blob());
    //                 }
    //                 product.isLoaded = true;
    //                 promises.push(Promise.resolve());
    //             }
    //             await Promise.all(promises);
    //             return data;
    //         }
    //     };

    //     (async () => {
    //         var data = await getAllProducts();
    //         data = data.reverse();
    //         setUsers(data.filter((item) => item.hasOwnProperty("isLoaded")));
    //     })();
    // }, []);

    useEffect(() => {
        // Update the products state within the useEffect hook
        setProducts([
            {
                id: 1,
                name: "Product 1 h h h h h hiihihihih hihihi",
                price: 19.99,
                image: "product1.jpg",
            },
            {
                id: 2,
                name: "Product 2",
                price: 29.99,
                image: "product2.jpg",
            },
            {
                id: 3,
                name: "Product 3",
                price: 39.99,
                image: "product3.jpg",
            },
            {
                id: 4,
                name: "Product 4",
                price: 49.99,
                image: "product4.jpg",
            },
            {
                id: 5,
                name: "Product 5",
                price: 59.99,
                image: "product5.jpg",
            }
        ]);
    }, []);

    return (
        <Container fluid className="pt-5 content-page">
                <Row>
                    <Col sm={3}>
                        <FilterPanel />
                    </Col>
                    <Col sm={9}>
                        <Container fluid>
                            <Row>
                                <ProductCard products={products}></ProductCard>
                            </Row>
                        </Container>
                    </Col>
                </Row>
        </Container>
    );
}

export default Content;
