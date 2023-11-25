package vn.edu.tdtu.springcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account add(Account requestAccount) {
        Account account = new Account();
        account.setEmail(requestAccount.getEmail());
        account.setPassword(requestAccount.getPassword());
        account.setName(requestAccount.getName());
        account.setPhone(requestAccount.getPhone());
        account.setAddress(requestAccount.getAddress());
        account.setEnabled(requestAccount.getEnabled());
        account.setIsAdmin(requestAccount.getIsAdmin());
        return accountRepository.save(account);
    }

    public Account findById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        return optionalAccount.orElse(null);
    }

    @Override
    public Account loadUserByUsername(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return null;
        }
        return account;
    }
}
