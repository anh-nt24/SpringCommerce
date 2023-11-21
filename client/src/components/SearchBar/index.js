import React from 'react';
import { useState } from 'react';
import styled from 'styled-components';

const Form = styled.form`
    display: flex;
    line-height: 28px;
    align-items: center;
    position: relative;
    max-width: 600px;
`;

const Input = styled.input`
    height: 40px;
    line-height: 28px;
    padding: 0 1rem;
    width: 100%;
    padding-left: 2.5rem;
    border: 2px solid transparent;
    border-radius: 5px;
    outline: none;
    background-color: #F5A694;
    color: #000;
    box-shadow: 0 0 5px #C1D9BF, 0 0 0 10px #f5f5f5eb;
    transition: 0.3s ease;

    &::placeholder {
        color: #555;
    }
`;

const Icon = styled.svg`
    position: absolute;
    left: 1rem;
    fill: #777;
    width: 1rem;
    height: 1rem;
`;

const SearchBar = ({setText, handleSubmit}) => {

	const handleChange = (event) => {
		const value = event.target.value;
		setText(value);
	}

	const handleKeyDown = (event) => {
		if (event.key === 'Enter') {
			handleSubmit(event);
		}
	};

	return (
		<Form className="group" style={{ width: "100%" }}>
			<Icon className="icon" aria-hidden="true" viewBox="0 0 24 24">
				<g>
					<path d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"></path>
				</g>
			</Icon>
			<Input onChange={handleChange} onKeyDown={handleKeyDown} placeholder="Search" type="search" className="input" />
		</Form>
	);
};

export default SearchBar;
