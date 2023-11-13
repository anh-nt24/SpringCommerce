package vn.edu.tdtu.springcommerce.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.tdtu.springcommerce.dto.ProductDTO;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.entity.Product;
import vn.edu.tdtu.springcommerce.entity.ProductImage;
import vn.edu.tdtu.springcommerce.entity.Seller;
import vn.edu.tdtu.springcommerce.repository.CategoryRepository;
import vn.edu.tdtu.springcommerce.repository.ProductImageRepository;
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

    @Autowired
    private ProductImageRepository imageRepository;

    // Create a new product
    public Boolean createProduct(ProductDTO productDTO, MultipartFile imageFile) {
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

        Product savedProduct = productRepository.save(product);

        if (imageFile != null && !imageFile.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, filename).toString();

            try (OutputStream os = new FileOutputStream(filePath)) {
                os.write(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            ProductImage productImage = new ProductImage();
            productImage.setImgUrl(filePath);
            productImage.setProduct(savedProduct);
            imageRepository.save(productImage);
        }
        return true;
    }

    // Retrieve a product by ID
    public ProductDTO getProductById(Integer productId) {
        Product product = productRepository.findActiveProductById(productId);
        if (product != null) {
            try {
                ProductImage productImage = imageRepository.getImageByProductId(productId);
                String imageUrl = "";
                if (productImage != null) {
                    imageUrl = productImage.getImgUrl();
                }
                return mapProductToDTO(product, imageUrl);
            } catch (Exception e) {
                System.out.println("Error:");
                e.printStackTrace();
            }

        }
        return null;
    }

    // Update a product by ID
    public Boolean updateProduct(Integer productId, ProductDTO productDTO, MultipartFile imageFile) {
        Product product = productRepository.findActiveProductById(productId);

        if (product != null) {
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(productDTO.getPrice());
            product.setStockQuantity(productDTO.getStockQuantity());
            Product updatedProduct = productRepository.save(product);

            if (imageFile != null && !imageFile.isEmpty()) {
                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                String filePath = Paths.get(uploadDirectory, filename).toString();

                try (OutputStream os = new FileOutputStream(filePath)) {
                    os.write(imageFile.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
                ProductImage productImage = imageRepository.getImageByProductId(updatedProduct.getId());
                if (productImage == null) {
                    productImage = new ProductImage();
                }
                productImage.setImgUrl(filePath);
                productImage.setProduct(updatedProduct);
                imageRepository.save(productImage);
            }
            return true;
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
    private ProductDTO mapProductToDTO(Product product, String image) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setImageUrl(image);
        productDTO.setCategoryId(product.getCategoryId().getId());
        productDTO.setShopId(product.getShopId().getId());
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAllActiveProducts();

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            ProductImage image = imageRepository.getImageByProductId(product.getId());
            String imageUrl = "";
            if (image != null) {
                imageUrl = image.getImgUrl();
            }
            ProductDTO productDTO = mapProductToDTO(product, imageUrl);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
}
