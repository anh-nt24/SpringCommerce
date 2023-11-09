package vn.edu.tdtu.springcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.tdtu.springcommerce.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmailAndPassword(String email, String password);
}
