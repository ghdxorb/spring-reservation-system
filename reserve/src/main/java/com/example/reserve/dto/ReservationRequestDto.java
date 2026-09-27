package com.example.reserve.dto;

import java.time.LocalDateTime;

public class ReservationRequestDto {

    private Long userId;
    private Long itemId;
    private LocalDateTime reserveDateTime;

    public ReservationRequestDto() {}

    public ReservationRequestDto(Long userId, Long itemId, LocalDateTime reserveDateTime) {
        this.userId = userId;
        this.itemId = itemId;
        this.reserveDateTime = reserveDateTime;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public LocalDateTime getReserveDateTime() { return reserveDateTime; }
    public void setReserveDateTime(LocalDateTime reserveDateTime) { this.reserveDateTime = reserveDateTime; }
}