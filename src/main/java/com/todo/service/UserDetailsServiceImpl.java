package com.todo.service;

import com.todo.entity.User;
import com.todo.repository.UserRepository;
import com.todo.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(User.normalizeEmail(email))
                .map(UserPrincipal::from)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
