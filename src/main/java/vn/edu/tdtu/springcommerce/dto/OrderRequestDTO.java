package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Integer customerId;
    private Double totalPrice;
    private String status;
    private List<OrderDetailDTO> orderDetails;
}
