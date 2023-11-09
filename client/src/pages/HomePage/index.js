import MainLayout from "../../layouts/MainLayout";
import Content from "../../components/Content"
import Filter from "../../components/Filter"
import Container from "react-bootstrap/esm/Container";
import Row from "react-bootstrap/esm/Row";
import Col from "react-bootstrap/esm/Col";
import { useState } from "react";

const Main = () => {

    return (
        <MainLayout className='Homepage'>
            <Content />
        </MainLayout>
    );
};




export default Main;