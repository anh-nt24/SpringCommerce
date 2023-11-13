package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.dto.AccountDTO;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Value("${salt_password}")
    private String SALT;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account add(AccountDTO accountDTO) {
        String hashedPassword = bcrypt.encode(accountDTO.getPassword() + SALT);
        Account account = new Account();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(hashedPassword);
        account.setName(accountDTO.getName());
        account.setPhone(accountDTO.getPhone());
        account.setAddress(accountDTO.getAddress());
        return accountRepository.save(account);
    }

    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    public boolean validatePassword(String password, String userPassword) {
        return bcrypt.matches(password + SALT, userPassword);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Boolean find(Account account) {
        Account foundAccount = accountRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());
        return foundAccount != null;
    }
}
