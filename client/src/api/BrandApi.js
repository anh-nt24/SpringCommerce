import axios from 'axios'

export const GetAllBrand = async () => {
    const { data } = await axios.get(
        'http://localhost:8080/api/brand'
    );
    return data
}