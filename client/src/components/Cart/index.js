import React from 'react';
import { useCookies } from 'react-cookie';
import { Link, useNavigate } from 'react-router-dom';

const Cart = () => {
    const navigate = useNavigate();
	const [cookies] = useCookies(['user', 'id', 'token']);
	const { name, id, token } = cookies;

    const handleCartClick = () => {
        if (!token) {
            navigate('/login');
        } else {
            navigate('/cart');
        }
    };

    return (
        <div className='cart' onClick={handleCartClick}>
            <i className="fas fa-shopping-cart"></i>
        </div>
    );
};

export default Cart;
