import styled from 'styled-components';
import SearchBar from '../SearchBar';
import Navbar from 'react-bootstrap/esm/Navbar';
import Container from 'react-bootstrap/esm/Container';
import Nav from 'react-bootstrap/esm/Nav';
import Image from 'react-bootstrap/esm/Image'
import logo from '../../images/logo.png';
import { useState } from 'react';
import {
    MDBContainer,
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarToggler,
    MDBIcon,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBNavbarLink,
    MDBBtn,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBCollapse,
  } from 'mdb-react-ui-kit';
import Col from 'react-bootstrap/esm/Col';
import Row from 'react-bootstrap/esm/Row';
import Cart from '../Cart';


const NavBar = () => {
     // return (
    //     <Navbar className='navbar py-4'>
    //         <Container>
    //             <Navbar.Brand>
    //                 <Image width="200px" height="50px" src={logo} alt="" />
    //             </Navbar.Brand>
    //             <Navbar.Toggle aria-controls="basic-navbar-nav" />
    //             <Navbar.Collapse id="basic-navbar-nav">
    //                 <Nav className="ml-auto">
    //                     <SearchBar />
    //                 </Nav>
    //             </Navbar.Collapse>
    //         </Container>
    //     </Navbar>
    // )
    const [openBasic, setOpenBasic] = useState(false);
    return (
        <div className='navbar'>
            <Container className='py-3'>
                <Row className='w-100'>
                    <Col>
                        <MDBNavbar expand='md'>
                            <MDBContainer fluid>
                                <MDBNavbarBrand href='#'>
                                    <Image width="200px" height="50px" src={logo} alt="" />
                                </MDBNavbarBrand>

                                <MDBNavbarToggler
                                    aria-controls='navbarSupportedContent'
                                    aria-expanded='false'
                                    aria-label='Toggle navigation'
                                    onClick={() => setOpenBasic(!openBasic)}
                                >
                                    <MDBIcon icon='bars' fas />
                                </MDBNavbarToggler>

                                <MDBCollapse navbar open={openBasic}>
                                    <MDBNavbarNav className='custom-nav mr-auto w-0 mb-2 mb-lg-0'>
                                        <MDBNavbarItem>
                                        </MDBNavbarItem>
                                    </MDBNavbarNav>
                                    <SearchBar />
                                    <Cart />
                                </MDBCollapse>
                            </MDBContainer>
                        </MDBNavbar>
                    </Col>
                </Row>
            </Container>
        </div>
      );
}

export default NavBar;