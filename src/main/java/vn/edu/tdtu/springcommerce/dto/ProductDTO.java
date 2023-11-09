package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.entity.Seller;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private Boolean isActive;
    private String image;
    private Integer categoryId;
    private Integer shopId;

}
