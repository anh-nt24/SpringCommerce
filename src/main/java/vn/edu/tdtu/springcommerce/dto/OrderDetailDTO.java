package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Integer productId;
    private Double price;
    private Integer quantity;
}
