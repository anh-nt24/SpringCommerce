package vn.edu.tdtu.springcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;


    @Column(name = "color")
    private String color;

    @Column(name = "is_active", columnDefinition = "BOOLEAN default true", nullable = false)
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brandId;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shopId;
}
