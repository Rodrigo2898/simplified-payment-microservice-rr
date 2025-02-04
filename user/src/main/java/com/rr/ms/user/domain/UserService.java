package com.rr.ms.user.domain;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDomain user) {
        userRepository.save(user);
    }

    public List<UserDomain> findAll() {
        return userRepository.findAll();
    }

    public UserDomain findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
