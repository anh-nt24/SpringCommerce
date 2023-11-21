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
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';


const NavBar = ({setText, handleSubmit}) => {
    const [openBasic, setOpenBasic] = useState(false);
    const [cookies] = useCookies(['user', 'id', 'token']);
    const { name, id, token } = cookies;
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
                                    <SearchBar setText={setText} handleSubmit={handleSubmit}/>
                                    <Cart />
                                    <span style={{paddingRight: "7px"}} className='link-style-none'>|</span>
                                    {name ? <span style={{color: "white"}}> {name} </span> : 
                                        <Link className='link-style-none' to="/login"> Login </Link>
                                    }
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