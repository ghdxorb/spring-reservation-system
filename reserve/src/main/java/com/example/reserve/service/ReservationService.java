package com.example.reserve.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reserve.entity.Item;
import com.example.reserve.entity.Reservation;
import com.example.reserve.entity.Reservation.ReservationStatus;
import com.example.reserve.entity.User;
import com.example.reserve.repository.ItemRepository;
import com.example.reserve.repository.ReservationRepository;
import com.example.reserve.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              ItemRepository itemRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    // 1. 예약 생성 (userId, itemId, reserveDateTime)
    @Transactional
    public Long createReservation(Long userId, Long itemId, LocalDateTime reserveDateTime) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. ID: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. ID: " + itemId));

        // 이미 해당 시간에 확정된(CONFIRMED) 예약이 있는지 중복 체크
        boolean exists = reservationRepository.existsByItemIdAndReserveDateTimeAndStatus(
                itemId, reserveDateTime, ReservationStatus.CONFIRMED);

        if (exists) {
            throw new IllegalStateException("해당 시간에 이미 완료된 예약이 존재합니다.");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .item(item)
                .reserveDateTime(reserveDateTime)
                .status(ReservationStatus.CONFIRMED)
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation.getId();
    }

    // 2. 예약 취소
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약입니다. ID: " + reservationId));

        reservation.setStatus(ReservationStatus.CANCELED);
    }

    // 3. 전체 예약 목록 조회
    public List<Reservation> findReservations() {
        return reservationRepository.findAll();
    }

    // 4. 특정 유저의 예약 목록 조회
    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }
}