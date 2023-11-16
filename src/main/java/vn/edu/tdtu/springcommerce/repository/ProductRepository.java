package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.isActive = true")
    Product findActiveProductById(@Param("productId") Integer productId);

    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> findAllActiveProducts();

    @Query("SELECT p FROM Product p " +
            "WHERE (:categoryId IS NULL OR p.categoryId = :categoryId) " +
            "AND (:brandId IS NULL OR p.brandId = :brandId) " +
            "AND (:color IS NULL OR p.color = :color) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
            "AND p.isActive = true")
    List<Product> findByFilters(
            @Param("categoryId") Integer categoryId,
            @Param("brandId") Integer brandId,
            @Param("color") String color,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice
    );
}
