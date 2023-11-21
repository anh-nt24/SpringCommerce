import axios from 'axios'

export const GetAllCategory = async () => {
    const { data } = await axios.get(
        'http://localhost:8080/api/category'
    );
    return data
}