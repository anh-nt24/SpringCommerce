package vn.edu.tdtu.springcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.AccountDTO;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.service.AccountService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;
import vn.edu.tdtu.springcommerce.utils.TokenManager;
import vn.edu.tdtu.springcommerce.utils.Token;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AccountDTO accountDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword())
            );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("User not found", null));
        } catch (DisabledException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("User is disabled", null));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Invalid credentials", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Internal server error", null));
        }
        Account account = accountService.loadUserByUsername(accountDTO.getEmail());
        String jwtToken = tokenManager.generateJwtToken(account);
        Map<String, String> obj = new HashMap<>();
        obj.put("token", jwtToken);
        obj.put("id", account.getId().toString());
        obj.put("name", account.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("Login successfully", obj));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountDTO accountDTO) {
        try {
            String email = accountDTO.getEmail();

            // Check if the email is already registered
            if (accountService.loadUserByUsername(email) != null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse("Email is already registered.", null));
            }

            Account account = new Account.Builder()
                    .email(accountDTO.getEmail())
                    .password(passwordEncoder.encode(accountDTO.getPassword()))
                    .enabled(true)
                    .name(accountDTO.getName())
                    .address(accountDTO.getAddress())
                    .phone(accountDTO.getPhone())
                    .isAdmin(accountDTO.getIsAdmin() == null ? false : accountDTO.getIsAdmin())
                    .build();

            Account savedUser = accountService.add(account);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("User registered successfully.", savedUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Internal Server Error", null));
        }
    }
}





