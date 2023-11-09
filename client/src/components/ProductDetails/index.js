import React from 'react';
import Col from 'react-bootstrap/esm/Col';


const ProductDetails = () => (
	<Col sm={6} className="push-bit">
		<div className="clearfix">
			<div className="pull-right">
				<span className="h2">
					<strong>$ 69</strong>
				</span>
			</div>
			<span className="h4">
				<strong style={{color: "rgb(253,87,48)"}}>Table super power n2000</strong>
				<br />
			</span>
		</div>
		<hr />
		<p>
			Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam quis ligula elit. Aliquam at orci ac neque semper
			dictum. Sed tincidunt scelerisque ligula, et facilisis nulla hendrerit non. Suspendisse potenti. Pellentesque
			non accumsan orci.
		</p>
		<p>
			Sed porttitor pretium venenatis. Suspendisse potenti. Aliquam quis ligula elit. Aliquam at orci ac neque semper
			dictum. Sed tincidunt scelerisque ligula, et facilisis nulla hendrerit non. Suspendisse potenti. Pellentesque
			non accumsan orci.
		</p>
	</Col>
);

export default ProductDetails;