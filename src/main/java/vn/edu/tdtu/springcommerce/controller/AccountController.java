package vn.edu.tdtu.springcommerce.controller;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.*;
import vn.edu.tdtu.springcommerce.dto.AccountDTO;
import vn.edu.tdtu.springcommerce.entity.Account;
import vn.edu.tdtu.springcommerce.service.AccountService;
import vn.edu.tdtu.springcommerce.utils.ApiResponse;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private static final Key JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AccountDTO accountDTO) {
        try {
            String email = accountDTO.getEmail();
            String password = accountDTO.getPassword();

            // Check if the user exists
            Account user = accountService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse("Invalid email", null));
            }

            // Check if the provided password matches the stored hashed password
            boolean isPasswordValid = accountService.validatePassword(password, user.getPassword());
            if (!isPasswordValid) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse("Wrong password", null));
            }

            // Generate JWT token
            String token = Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("userId", user.getId())
                    .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY.getEncoded())
                    .compact();

            Map<String, String> obj = new HashMap<>();
            obj.put("token", token);
            obj.put("id", user.getId().toString());
            obj.put("name", user.getName());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Login successfully", obj));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Internal Server Error", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountDTO accountDTO) {
        try {
            String email = accountDTO.getEmail();

            // Check if the email is already registered
            if (accountService.existsByEmail(email)) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse("Email is already registered.", null));
            }

            Account savedUser = accountService.add(accountDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("User registered successfully.", savedUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Internal Server Error", null));
        }
    }
}





