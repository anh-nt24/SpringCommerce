package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.CategoryDTO;
import vn.edu.tdtu.springcommerce.service.CategoryService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDto) {
        try {
            Integer categoryId = categoryService.addCategory(categoryDto);
            return ResponseEntity.ok(new ApiResponse("Category added successfully", categoryId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error adding category", null));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDto) {
        try {
            categoryService.updateCategory(categoryId, categoryDto);
            return ResponseEntity.ok(new ApiResponse("Category updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error updating category", null));
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(new ApiResponse("Category deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting category", null));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<CategoryDTO> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error retrieving categories", null));
        }
    }
}
