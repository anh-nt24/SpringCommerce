package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
