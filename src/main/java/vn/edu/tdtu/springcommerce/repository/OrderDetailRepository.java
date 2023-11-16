package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Category;
import vn.edu.tdtu.springcommerce.entity.OrderDetails;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Integer> {
    List<OrderDetails> findByOrderId(Integer orderId);
}
