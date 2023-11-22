import axios from 'axios'

export const AddToCart = async (id, form) => {
    const { data } = await axios.post(
        'http://localhost:8080/api/cart?id=' + id,
        form
    );
    return data
}

export const GetAllCartItems = async (id) => {
    const { data } = await axios.get(
        'http://localhost:8080/api/cart?id=' + id
    );
    return data
}