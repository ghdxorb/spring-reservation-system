package com.example.reserve.controller;

import com.example.reserve.entity.User;
import com.example.reserve.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User API", description = "사용자 관리 API")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "사용자 등록", description = "새로운 사용자를 등록합니다.")
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody User user) {
        Long savedId = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @Operation(summary = "전체 사용자 조회", description = "등록된 모든 사용자 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @Operation(summary = "사용자 단건 조회", description = "ID로 특정 사용자를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @Operation(summary = "사용자 삭제", description = "ID로 특정 사용자를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}