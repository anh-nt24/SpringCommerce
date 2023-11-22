package vn.edu.tdtu.springcommerce.controller;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.tdtu.springcommerce.dto.ProductDTO;
import vn.edu.tdtu.springcommerce.dto.ProductFilterDTO;
import vn.edu.tdtu.springcommerce.entity.ProductImage;
import vn.edu.tdtu.springcommerce.service.ProductImageService;
import vn.edu.tdtu.springcommerce.service.ProductService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.security.Key;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Key JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> displayAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Add a new product
    @PostMapping
    public ResponseEntity<?> createProduct(
            @Valid ProductDTO productDTO,
            @RequestParam(name = "image") MultipartFile imageFile) {
        Boolean result = productService.createProduct(productDTO, imageFile);
        if (result) {
            ApiResponse response = new ApiResponse("Product added succesfully", null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            ApiResponse response = new ApiResponse("Product added failed", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Retrieve a product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        ProductDTO productDTO = productService.getProductById(productId);
        if (productDTO == null) {
            ApiResponse response = new ApiResponse("Product not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    // Update a product by ID
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId,
                                           @Valid ProductDTO productDTO,
                                           @RequestParam(name = "image") MultipartFile imageFile) {
        Boolean result = productService.updateProduct(productId, productDTO, imageFile);
        if (result) {
            ApiResponse response = new ApiResponse("Product updated succesfully", null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        else {
            ApiResponse response = new ApiResponse("Product updated failed", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable Integer productId,
            @RequestHeader("Authorization") String token) {
        try {
            // Validate the JWT token
//            validateToken(token);

            // Your existing logic for deleting the product
            boolean deleted = productService.deleteProduct(productId);

            ApiResponse response;
            HttpStatus httpStatus;

            if (deleted) {
                response = new ApiResponse("Product deleted successfully", null);
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                response = new ApiResponse("Product not found", null);
                httpStatus = HttpStatus.NOT_FOUND;
            }

            return new ResponseEntity<>(response, httpStatus);
        } catch (ExpiredJwtException ex) {
            return new ResponseEntity<>(new ApiResponse("JWT token has expired", null), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse("Invalid JWT token", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<?> filterProducts(@RequestBody ProductFilterDTO filterDTO) {
        try {
            List<ProductDTO> filteredProducts = productService.filterProducts(
                    filterDTO.getCategoryId(),
                    filterDTO.getBrandId(),
                    filterDTO.getColor(),
                    filterDTO.getMinPrice(),
                    filterDTO.getMaxPrice()
            );
            return ResponseEntity.ok(filteredProducts);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse response = new ApiResponse("Something went wrong", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

}






