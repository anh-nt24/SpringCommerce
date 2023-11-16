package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.BrandDTO;
import vn.edu.tdtu.springcommerce.service.BrandService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;


    @PostMapping
    public ResponseEntity<?> addBrand(@RequestBody BrandDTO brandDto) {
        try {
            Integer brandId = brandService.addBrand(brandDto);
            return ResponseEntity.ok(new ApiResponse("Brand added successfully", brandId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error adding brand", null));
        }
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<?> updateBrand(@PathVariable Integer brandId, @RequestBody BrandDTO brandDto) {
        try {
            brandService.updateBrand(brandId, brandDto);
            return ResponseEntity.ok(new ApiResponse("Brand updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating brand", null));
        }
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer brandId) {
        try {
            brandService.deleteBrand(brandId);
            return ResponseEntity.ok(new ApiResponse("Brand deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting brand", null));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<BrandDTO> categories = brandService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error retrieving categories", null));
        }
    }
}
