package com.example.reserve.dto;

import java.time.LocalDateTime;
import com.example.reserve.entity.Reservation;

public class ReservationResponseDto {
    private Long reservationId;
    private String userName;
    private String itemName;
    private LocalDateTime reserveDateTime;
    private String status;

    public ReservationResponseDto() {}

    public ReservationResponseDto(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.userName = reservation.getUser().getName();
        this.itemName = reservation.getItem().getName();
        this.reserveDateTime = reservation.getReserveDateTime();
        this.status = reservation.getStatus().name();
    }

    // Getter 메서드 추가 (Jackson JSON 직렬화에 필수)
    public Long getReservationId() { return reservationId; }
    public String getUserName() { return userName; }
    public String getItemName() { return itemName; }
    public LocalDateTime getReserveDateTime() { return reserveDateTime; }
    public String getStatus() { return status; }
}