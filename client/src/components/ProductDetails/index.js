import React from 'react';
import Col from 'react-bootstrap/esm/Col';


const ProductDetails = ({data}) => (
	<Col sm={6} className="push-bit">
		<div className="clearfix">
			<div className="pull-right">
				<span className="h2">
					<strong>$ {data.price}</strong>
				</span>
			</div>
			<span className="h4">
				<strong style={{color: "rgb(253,87,48)"}}>{data.name}</strong>
				<br />
			</span>
		</div>
		<hr />
		<p style={{whiteSpace: "pre-line"}}>
			{ data.description }
		</p>
	</Col>
);

export default ProductDetails;