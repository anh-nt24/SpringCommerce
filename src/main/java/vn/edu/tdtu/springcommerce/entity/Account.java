package vn.edu.tdtu.springcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account implements UserDetails {

    public static class Builder {
        private String email;
        private String password;
        private boolean enabled;
        private String name;
        private String phone;
        private String address;

        private Boolean isAdmin;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder isAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.setEmail(email);
            account.setPassword(password);
            account.setPhone(phone);
            account.setAddress(address);
            account.setName(name);
            account.setEnabled(enabled);
            account.setIsAdmin(isAdmin);
            return account;
        }
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "enabled", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;

    @Column(name = "is_admin", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (this.isAdmin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled != null && this.enabled;
    }
}
