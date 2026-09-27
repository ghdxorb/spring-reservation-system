package com.example.reserve.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.reserve.dto.ReservationRequestDto;
import com.example.reserve.entity.Reservation;
import com.example.reserve.service.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reservation API", description = "예약 관리 API")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "예약 신청", description = "사용자가 특정 상품/공간을 예약합니다.")
    @PostMapping
    public ResponseEntity<Long> createReservation(@RequestBody ReservationRequestDto requestDto) {
        Long reservationId = reservationService.createReservation(
                requestDto.getUserId(),
                requestDto.getItemId(),
                requestDto.getReserveDateTime()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationId);
    }

    @Operation(summary = "전체 예약 목록 조회")
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.findReservations());
    }

    @Operation(summary = "사용자별 예약 목록 조회")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationsByUserId(userId));
    }

    @Operation(summary = "예약 취소")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}