import axios from 'axios'

export const AddToCart = async (id, form, token) => {
    const { data } = await axios.post(
        'http://localhost:8080/api/cart?uid=' + id,
        form,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );
    return data
}

export const GetAllCartItems = async (id, token) => {
    const { data } = await axios.get(
        'http://localhost:8080/api/cart?uid=' + id,
        {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }
    );
    return data
}
