import React, { useEffect, useState } from 'react';
import Form from "react-bootstrap/esm/Form"
import { GetAllBrand } from '../../api/BrandApi';
import { GetAllCategory } from '../../api/CategoryApi';
import { GetAllColor } from '../../api/ProductApi';

const FilterPanel = ({ onFilter }) => {

    const [categories, setCategory] = useState(null);
    const [brand, setBrand] = useState(null);
    const [colors, setColor] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const categoryData = await GetAllCategory();
                setCategory(categoryData);

                const brandData = await GetAllBrand();
                setBrand(brandData);

                setColor(await GetAllColor());
            } catch (error) {
                console.error('Error fetching category data:', error);
            }
        };
        fetchData();
      }, []);

    const [filterValue, setFilterValue] = useState('');

    const handleFilterChange = (e) => {
        setFilterValue(e.target.value);
    };

    const handleApplyFilter = () => {
        onFilter(filterValue);
    };

    return (
        <Form className="filter-panel p-3 filter-panel" style={{background: "#fff"}}>
            <h5>
                <i className="fa-solid fa-filter pr-2" style={{
                    fontSize: "13px",
                    marginRight: "10px",
                }}></i>
                FILTER PANEL
            </h5>
            <fieldset>
                <h6>Category</h6>
                <ul style={{
                    listStyle: "none"
                }}>
                    {categories && categories.map(category => (
                        <li key={category.id}>
                            <input name='category[]' type="checkbox" id={`category${category.id}`} value={category.id}/>
                            <label style={{ paddingLeft: "5px" }} htmlFor={`category${category.id}`}>
                            {category.name}
                            </label>
                        </li>
                    ))}
                </ul>
                <hr />
                <h6>Brand</h6>
                <ul style={{
                    listStyle: "none"
                }}>
                    {brand && brand.map(b => (
                        <li key={b.id}>
                            <input name='brand[]' type="checkbox" id={`brand${b.id}`} value={b.id}/>
                            <label style={{ paddingLeft: "5px" }} htmlFor={`brand${b.id}`}>
                            {b.name}
                            </label>
                        </li>
                    ))}
                </ul>
                <hr />
                <h6>Color</h6>
                <ul style={{
                    listStyle: "none"
                }}>
                    {colors && colors.map((color, index) => (
                        <li key={index}>
                            <input name='color[]' type="checkbox" id={`color${index}`} value={color} />
                            <label style={{ paddingLeft: "5px" }} htmlFor={`color${index}`}>
                            {color}
                            </label>
                        </li>
                    ))}
                </ul>
                <hr />
                <h6>Price range</h6>
                <input style={{width: "80px", marginRight: "5px"}} placeholder="min" value="" className='mr-1' name='minPrice' type='number'/>
                <input style={{width: "80px", marginRight: "5px"}} placeholder="max" value="" className='mr-1' name='maxPrice' type='number'/>
                <hr/>
            </fieldset>
            <button className='btn main-btn' onClick={handleApplyFilter}>Apply Filter</button>
        </Form>
    );
};

export default FilterPanel;
