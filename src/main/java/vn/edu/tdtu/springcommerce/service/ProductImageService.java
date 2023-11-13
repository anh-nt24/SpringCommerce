package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.entity.ProductImage;
import vn.edu.tdtu.springcommerce.repository.ProductImageRepository;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository imageRepository;

    public ProductImage getImageById(Integer productId) {
        return imageRepository.getImageByProductId(productId);
    }
}
