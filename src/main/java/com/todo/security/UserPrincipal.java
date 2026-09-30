package com.todo.security;

import com.todo.entity.Role;
import com.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserPrincipal implements UserDetails, CredentialsContainer {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String email;
    private String password;
    private final Role role;

    public static UserPrincipal from(User user){
        return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
