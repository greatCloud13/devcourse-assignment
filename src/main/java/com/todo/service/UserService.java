package com.todo.service;

import com.todo.dto.request.SignupRequestDto;
import com.todo.dto.response.UserDto;
import com.todo.entity.User;
import com.todo.global.exception.UsingEmailException;
import com.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto register(SignupRequestDto request){

        if(userRepository.existsByEmail(request.getEmail()) == true){
            throw new UsingEmailException();
        }

        User user = new User(
                request.getEmail(),
                request.getNickname(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return user.toDto();
    }


}
