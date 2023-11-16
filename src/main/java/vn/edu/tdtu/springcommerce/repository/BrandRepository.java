package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Brand;
import vn.edu.tdtu.springcommerce.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findByIsActiveTrue();

    Optional<Brand> findByIdAndIsActiveTrue(Integer brandId);
}
