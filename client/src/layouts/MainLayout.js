import NavBar from "../components/NavBar";

import { useEffect, useState } from "react";

import { GetAllProducts } from '../api/ProductApi';

const apiUrl = process.env.REACT_APP_API_URL;

const MainLayout = ({children, page}) => {
    const [text, setText] = useState('');
    const [filteredProducts, setFilteredProducts] = useState(null);
    const [products, setProducts] = useState([]);
    const [isSearch, setSearch] = useState(false);

	const handleSubmit = (event) => {
        setFilteredProducts(null)
		event.preventDefault();
        filterProducts()
	};


    const filterProducts = () => {
        const filtered = products.filter(product =>
            product.name.toLowerCase().includes(text.toLowerCase())
        );
        setFilteredProducts(filtered);
    };

    useEffect(() => {
        document.title = 'Shop - Homepage';

        const fetchData = async () => {
            if (!isSearch) {
                const response = await GetAllProducts();
                if (response.status === 200) {
                    var data = response.data;
                    const promises = [];
                    for (const product of data) {
                        const img_response = await fetch(apiUrl + product.imageUrl);
                        if (img_response.ok) {
                            product.imageUrl = URL.createObjectURL(await img_response.blob());
                            product.isLoaded = true;
                            promises.push(Promise.resolve());
                        } else {
                            console.error(`Failed to fetch image. Status: ${img_response.status}`);
                        }
                    }
                    await Promise.all(promises);
                    setProducts(data);
                    return data;
                }
            } else {
                setProducts([]);
            }
        };
        fetchData();
    }, []);

    return (
        <>
            {!page && <NavBar setText={setText} handleSubmit={handleSubmit}/>}
            <div>
                {filteredProducts ? (
                    children(filteredProducts)
                ) : (
                    children(products)
                )}
            </div>
            <footer className="p-4 footer">
                <div className="text-center">
                © Copyright fakeshopee.com 2023. All rights reserved.
                </div>
            </footer>
        </>
    );
}

export default MainLayout;