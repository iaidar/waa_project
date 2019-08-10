package com.project.onlinestore.security.service;

import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.respository.UserRepository;
import com.project.onlinestore.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().getId()== SecurityConstants.ROLE_BUYER)
            user.setActive(1);
        else if (user.getRole().getId()== SecurityConstants.ROLE_SELLER)
            user.setActive(0);
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
