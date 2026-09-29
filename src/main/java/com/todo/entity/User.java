package com.todo.entity;

import com.todo.dto.response.UserDto;
import com.todo.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "USERS")
public class User extends BaseIdAndTime {

    private String email;

    private String nickname;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String email, String nickname, String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public UserDto toDto(){
        return new UserDto(
                this.getEmail(),
                this.getNickname(),
                this.getCreateDate());
    }
}
