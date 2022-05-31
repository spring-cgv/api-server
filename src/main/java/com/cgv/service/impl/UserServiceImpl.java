package com.cgv.service.impl;

import com.cgv.domain.Role;
import com.cgv.domain.dto.UserDto;
import com.cgv.domain.entity.User;
import com.cgv.repository.UserRepository;
import com.cgv.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .pwd(passwordEncoder.encode(userDto.getPwd()))
                .name(userDto.getName())
                .birth(userDto.getBirth())
                .gender(userDto.getGender())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
    }
}
