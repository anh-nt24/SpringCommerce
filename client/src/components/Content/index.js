import React from 'react'
import Container from 'react-bootstrap/esm/Container';
import Row from 'react-bootstrap/esm/Row';
import { useState } from 'react';
import ProductCard from '../ProductCard';
import { useEffect } from 'react';
import Col from 'react-bootstrap/esm/Col';
import FilterPanel from '../Filter';
const Content = ({products}) => {
    
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
