package com.example.reserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reserve.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // 기본 CRUD 메서드(save, findById, findAll, deleteById 등) 자동 제공
}
