package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.tdtu.springcommerce.dto.ProductDTO;
import vn.edu.tdtu.springcommerce.service.ProductService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> displayAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Add a new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @RequestParam("image") MultipartFile imageFile,
            @ModelAttribute ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO, imageFile);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // Retrieve a product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId) {
        ProductDTO product = productService.getProductById(productId);
        if (product == null) {
            ApiResponse response = new ApiResponse(
                    "Product not found",
                    null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Update a product by ID
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productId, productDTO);
        if (updatedProduct == null) {
            ApiResponse response = new ApiResponse(
                    "Product not found",
                    null,
                    HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Delete a product by ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        boolean deleted = productService.deleteProduct(productId);

        ApiResponse response;
        HttpStatus httpStatus;

        if (deleted) {
            response = new ApiResponse("Product deleted successfully", null, HttpStatus.NO_CONTENT.value());
            httpStatus = HttpStatus.NO_CONTENT;
        } else {
            response = new ApiResponse("Product not found", null, HttpStatus.NOT_FOUND.value());
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(response, httpStatus);
    }


}






