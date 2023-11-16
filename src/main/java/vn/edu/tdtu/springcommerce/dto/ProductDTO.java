package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String color;
    private Integer stockQuantity;
    private Integer categoryId;
    private Integer brandId;
    private String imageUrl;
    private Integer shopId;
}


