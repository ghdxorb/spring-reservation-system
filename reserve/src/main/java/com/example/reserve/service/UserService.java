package com.example.reserve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reserve.entity.User;
import com.example.reserve.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 등록
    @Transactional
    public Long saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    // 전체 사용자 목록 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 단건 사용자 조회
    public User findOne(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. ID: " + userId));
    }

    // 사용자 삭제
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}