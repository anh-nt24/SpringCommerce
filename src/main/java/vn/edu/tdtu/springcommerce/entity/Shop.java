package vn.edu.tdtu.springcommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "seller")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "shop_name", length = 1000, nullable = false)
    private String shopName;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Account owner;
}

