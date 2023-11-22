package vn.edu.tdtu.springcommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductFilterDTO {
    private List<Integer> categoryId;
    private List<Integer> brandId;
    private List<String> color;
    private Integer minPrice;
    private Integer maxPrice;

}
