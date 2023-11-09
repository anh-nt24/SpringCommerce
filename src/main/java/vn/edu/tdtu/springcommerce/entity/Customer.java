package vn.edu.tdtu.springcommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "address", length = 500)
    private String address;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}

