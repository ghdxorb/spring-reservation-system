package com.example.reserve.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.reserve.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 특정 사용자의 모든 예약 목록 조회
    List<Reservation> findByUserId(Long userId);

    // 특정 상품의 특정 시간에 이미 확정된(CONFIRMED) 예약이 있는지 확인 (중복 예약 방지용)
    boolean existsByItemIdAndReserveDateTimeAndStatus(
        Long itemId, 
        LocalDateTime reserveDateTime, 
        Reservation.ReservationStatus status
    );

    // Fetch Join을 사용하여 N+1 문제 없이 예약 정보와 User, Item을 한 번에 조회
    @Query("SELECT r FROM Reservation r JOIN FETCH r.user JOIN FETCH r.item WHERE r.id = :id")
    Reservation findByIdWithUserAndItem(@Param("id") Long id);
}