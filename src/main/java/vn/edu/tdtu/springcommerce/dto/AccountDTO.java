package vn.edu.tdtu.springcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
    private Boolean isAdmin;
}
