package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Cart;
import vn.edu.tdtu.springcommerce.entity.CartItem;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByAccountEmail(String email);

    Cart findByAccountId(Integer id);
}
