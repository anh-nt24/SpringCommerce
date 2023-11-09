package vn.edu.tdtu.springcommerce.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.springcommerce.dto.ProductDTO;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.entity.Product;
import vn.edu.tdtu.springcommerce.entity.Seller;
import vn.edu.tdtu.springcommerce.repository.CategoryRepository;
import vn.edu.tdtu.springcommerce.repository.ProductRepository;
import vn.edu.tdtu.springcommerce.repository.SellerRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // Create a new product
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile imageFile) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        Seller seller = sellerRepository.findById(productDTO.getShopId()).orElse(null);

        if (category != null) {
            product.setCategoryId(category);
        }

        if (seller != null) {
            product.setShopId(seller);
        }

//        if (imageFile != null && !imageFile.isEmpty()) {
//            String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
//            System.out.println(filename);
//            String filePath = Paths.get(uploadDirectory, filename).toString();
//
//            try (OutputStream os = new FileOutputStream(filePath)) {
//                os.write(imageFile.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            product.setImage(filePath);
//        }
        product.setImage("");

        Product savedProduct = productRepository.save(product);
        return mapProductToDTO(savedProduct);
    }

    // Retrieve a product by ID
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findActiveProductById(productId);
        if (product != null) {
            return mapProductToDTO(product);
        }
        return null;
    }

    // Update a product by ID
    public ProductDTO updateProduct(Integer productId, ProductDTO productDTO) {
        Product product = productRepository.findActiveProductById(productId);

        if (product != null) {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStockQuantity(productDTO.getStockQuantity());
            product.setIsActive(productDTO.getIsActive());

            Product updatedProduct = productRepository.save(product);
            return mapProductToDTO(updatedProduct);
        }

        return null;
    }

    // Delete a product by ID
    public boolean deleteProduct(Integer productId) {
        Product product = productRepository.findActiveProductById(productId);

        if (product != null) {
            product.setIsActive(false);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    // Utility method to map a Product entity to a ProductDTO
    private ProductDTO mapProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setIsActive(product.getIsActive());
        productDTO.setCategoryId(product.getCategoryId().getId());
        productDTO.setShopId(product.getShopId().getId());
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAllActiveProducts();

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = mapProductToDTO(product);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
