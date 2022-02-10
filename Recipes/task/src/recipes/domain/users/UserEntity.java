package recipes.domain.users;

import recipes.domain.common.DomainError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserEntity implements UserDetails {

    private String username;
    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        validate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    public void encodePassword(UserPasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    private void validate() {
        var errors = new ArrayList<DomainError>();

        if (!username.contains("@") || !username.contains(".")) {
            errors.add(new DomainError("Username should contain @ and . symbols"));
        }

        if (password.length() < 8 || password.isBlank()) {
            errors.add(new DomainError("Password should contain at least 8 characters"));
        }

        if (errors.size() > 0) {
           throw new InvalidUserDetailsException(errors.toString());
        }

    }
}
