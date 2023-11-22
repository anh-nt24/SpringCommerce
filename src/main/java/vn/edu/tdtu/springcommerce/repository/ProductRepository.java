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
            "WHERE (:categoryIds IS NULL OR p.categoryId.id IN :categoryIds) " +
            "AND (:brandIds IS NULL OR p.brandId.id IN :brandIds) " +
            "AND (:colors IS NULL OR p.color IN :colors) " +
            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR p.price <= :maxPrice)" +
            "And p.isActive = true")
    List<Product> findByFilters(
            @Param("categoryIds") List<Integer> categoryIds,
            @Param("brandIds") List<Integer> brandIds,
            @Param("colors") List<String> colors,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice") Integer maxPrice
    );
}
