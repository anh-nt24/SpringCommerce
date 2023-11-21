import axios from 'axios'

export const GetAllProducts = async () => {
    return await axios.get(
        'http://localhost:8080/api/product'
    );
}

export const GetAllColor = async () => {
    const response = await axios.get(
        'http://localhost:8080/api/product'
    );
    if (response.status == 200) {
        const data = response.data;
        const uniqueColors = [...new Set(data.map(item => item.color))];
        return uniqueColors;
    }
}

export const GetProductDetails = async (id) => {
    return await axios.get(
        'http://localhost:8080/api/product/' + id
    );
}

export const SearchProduct = async (text) => {
    const config = {
        method: "POST",
        url: 'http://localhost:8080/api/account/login',
        data: text,
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    }
    return await axios(config);
}