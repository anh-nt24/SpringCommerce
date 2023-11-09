import React, { useState } from 'react';
import Form from "react-bootstrap/esm/Form"

const FilterPanel = ({ onFilter }) => {
  const [filterValue, setFilterValue] = useState('');

  const handleFilterChange = (e) => {
    setFilterValue(e.target.value);
  };

  const handleApplyFilter = () => {
    // Pass the filter value to the parent component for processing
    onFilter(filterValue);
  };

  return (
    <Form className="filter-panel mt-3 filter-panel">
        <h5>
            <i class="fa-solid fa-filter pr-2" style={{
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
                <li>
                    <input type="checkbox" id="category1" />
                    <label style={{paddingLeft: "5px"}} htmlFor="category1">Category 1</label>
                </li>
                <li>
                    <input type="checkbox" id="category2" />
                    <label style={{paddingLeft: "5px"}} htmlFor="category2">Category 2</label>
                </li>
                <li>
                    <input type="checkbox" id="category3" />
                    <label style={{paddingLeft: "5px"}} htmlFor="category3">Category 3</label>
                </li>
            </ul>
        </fieldset>
        <buttn className='btn main-btn' onClick={handleApplyFilter}>Apply Filter</buttn>
    </Form>
  );
};

export default FilterPanel;
