import React from 'react';
import Col from 'react-bootstrap/esm/Col';
import Row from 'react-bootstrap/esm/Row';

const imageUrls = [
	'https://www.bootdey.com/image/700x250/FF4500/000000',
	'https://www.bootdey.com/image/400x200/DA70D6/000000',
	'https://www.bootdey.com/image/400x200/4169E1/000000',
	'https://www.bootdey.com/image/400x200/40E0D0/000000',
];

const GallerySection = ({imageUrl}) => (
	<Col sm={6} className="push-bit">
		<a href="#/" className="gallery-link">
			<img src={imageUrl} width="100%" alt="" className="img-responsive push-bit" />
		</a>
	</Col>
);

export default GallerySection;