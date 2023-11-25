package vn.edu.tdtu.springcommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "is_canceled", nullable = false)
    private Boolean isCanceled;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Account customer;
}

