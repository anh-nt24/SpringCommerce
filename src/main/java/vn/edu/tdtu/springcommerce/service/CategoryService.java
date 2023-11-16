package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.dto.CategoryDTO;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Integer addCategory(CategoryDTO categoryDto) {
        Category category = mapDTOtoCategory(categoryDto);
        category.setIsActive(true);
        categoryRepository.save(category);
        return category.getId();
    }

    public void updateCategory(Integer categoryId, CategoryDTO categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findByIdAndIsActiveTrue(categoryId);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();

            // Update the category details
            existingCategory.setName(categoryDto.getName());
            if (categoryDto.getDescription() == null) {
                existingCategory.setDescription("");
            } else {
                existingCategory.setDescription(categoryDto.getDescription());
            }
            categoryRepository.save(existingCategory);
        }
    }


    public Boolean deleteCategory(Integer categoryId) {
        try {
            Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
            if (existingCategory != null) {
                existingCategory.setIsActive(false);
                categoryRepository.save(existingCategory);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> activeCategories = categoryRepository.findByIsActiveTrue();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();

        for (Category category : activeCategories) {
            CategoryDTO categoryDTO = mapCategoryToDTO(category);
            categoryDTOs.add(categoryDTO);
        }

        return categoryDTOs;
    }

    private CategoryDTO mapCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Category mapDTOtoCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getDescription() == null) {
            category.setDescription("");
        } else {
            category.setDescription(categoryDTO.getDescription());
        }
        return category;
    }
}
