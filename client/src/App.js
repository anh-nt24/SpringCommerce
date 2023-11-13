import HomePage from './pages/HomePage';
import CartPage from './pages/CartPage';
import Login from './pages/Login';
import ProductDetailsPage from './pages/ProductDetail';
import NotFoundPage from './pages/NotFound';
import {BrowserRouter, Routes, Route } from 'react-router-dom';

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/cart" element={<CartPage />} />
                <Route path="/details/:productId" element={<ProductDetailsPage />} />
                <Route path="/login" element={<Login />} />

                <Route path="*" element={<NotFoundPage />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;