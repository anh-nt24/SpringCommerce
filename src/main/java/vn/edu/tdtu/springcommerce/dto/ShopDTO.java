package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {
    private String shopName;
    private String description;
    private Integer owner;
}
