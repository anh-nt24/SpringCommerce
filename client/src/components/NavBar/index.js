import SearchBar from '../SearchBar';
import Container from 'react-bootstrap/esm/Container';
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
    MDBCollapse,
  } from 'mdb-react-ui-kit';
import Col from 'react-bootstrap/esm/Col';
import Row from 'react-bootstrap/esm/Row';
import Cart from '../Cart';


const NavBar = () => {
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