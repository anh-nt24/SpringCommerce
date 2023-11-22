import Col from 'react-bootstrap/esm/Col';
import Card from 'react-bootstrap/esm/Card';
import { Link, useNavigate } from 'react-router-dom';
import { AddToCart } from '../../api/CartApi';
import { useCookies } from 'react-cookie';

const ProductCard = ({ products }) => {
    const navigate = useNavigate();
    const [cookies] = useCookies(['user', 'id', 'token']);
    const { name, id, token } = cookies;
    const handleAddToCart = async (event) => {
        event.preventDefault();
        if (!id) {
            navigate('/login');
        }
        const productId = event.target.form.productId.value;
        const response = await AddToCart(id, {
            'productId': productId,
            'quantity': 1
        });
        if (response) {
            navigate('/cart')
        }
        
      };
    return (
        <> 
            {products && products.map((product) => (
                <Col key={product.id} lg={3} md={6} sm={12} className="pb-1">
                    <Card className="product-item border-0 mb-4">
                        <div className="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <Card.Img
                                src={product.imageUrl}
                                alt="product image"
                                className="img-fluid w-100"
                            />
                        </div>

                        <Card.Body className="border-left border-right pt-4 pb-3" style={{
                            paddingLeft: ".5rem", 
                            paddingRight: ".5rem" ,
                        }}>
                            <Link to={`/details/${product.id}`} className="btn btn-sm text-dark p-0">
                                <h6 className="product-name mb-3">
                                        {product.name}
                                </h6>
                            </Link>
                            <div className="d-flex">
                                <b className="price-product" style={{
                                    color: "red",
                                }}>${product.price}</b>
                            </div>
                        </Card.Body>
                        <Card.Footer className="d-flex justify-content-between bg-light border">
                            <form>
                                <input type="hidden" readOnly={true} value={product.id} name="productId" />
                                <button onClick={handleAddToCart} className="btn main-btn btn-sm text-dark p-0 px-2 m-auto">
                                    <i className="fas fa-shopping-cart text-primary mr-1"></i>
                                    Add To Cart
                                </button>
                            </form>
                        </Card.Footer>
                    </Card>
                </Col>
            ))}
        </>
    );
}

export default ProductCard;
