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
        'http://localhost:8080/api/product/detail?pid=' + id
    );
}

export const FilterProduct = async (form) => {
    const config = {
        method: "POST",
        url: 'http://localhost:8080/api/product/filter',
        data: form,
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    }
    return await axios(config);
}
