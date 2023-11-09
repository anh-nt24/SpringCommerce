import React from 'react';
import Col from 'react-bootstrap/esm/Col';
import Row from 'react-bootstrap/esm/Row';

const imageUrls = [
	'https://www.bootdey.com/image/700x250/FF4500/000000',
	'https://www.bootdey.com/image/400x200/DA70D6/000000',
	'https://www.bootdey.com/image/400x200/4169E1/000000',
	'https://www.bootdey.com/image/400x200/40E0D0/000000',
];

const GallerySection = () => (
	<Col sm={6} className="push-bit">
		<a href="#/" className="gallery-link">
			<img src={imageUrls[0]} width="100%" alt="" className="img-responsive push-bit" />
		</a>
		<Row className='push-bit'>
			{imageUrls.slice(1).map((url, index) => (
				<Col key={index} xs={4}>
					<a href="#/" className="gallery-link">
					<img src={url} width="100%" alt="" className="img-responsive" />
					</a>
				</Col>
			))}
		</Row>
	</Col>
);

export default GallerySection;