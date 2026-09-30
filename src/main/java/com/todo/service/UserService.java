package com.todo.service;

import com.todo.dto.request.SignupRequestDto;
import com.todo.dto.response.UserDto;
import com.todo.entity.User;
import com.todo.global.exception.CustomException;
import com.todo.global.exception.UsingEmailException;
import com.todo.global.exception.errorcode.ErrorCode;
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
    public UserDto register(SignupRequestDto request) {

//      정규화
        String normalizedEmail = User.normalizeEmail(request.getEmail());

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new UsingEmailException();
        }

        User user = new User(
                normalizedEmail,
                request.getNickname(),
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return user.toDto();
    }

    public UserDto getByEmail(String email) {
        String normalizedEmail = User.normalizeEmail(email);
        return userRepository.findByEmail(normalizedEmail)
                .map(User::toDto)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_CREDENTIALS));
    }
}