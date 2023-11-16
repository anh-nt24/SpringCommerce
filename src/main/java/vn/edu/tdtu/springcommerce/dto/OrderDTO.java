package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Date date;
    private Boolean isCanceled;
    private Integer customerId;
}
