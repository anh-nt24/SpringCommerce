import React from 'react';
import { Link } from 'react-router-dom';

const Cart = () => {
  return (
    <Link to="cart" className='cart'>
        <i className="fas fa-shopping-cart"></i>
    </Link>
  );
};

export default Cart;
