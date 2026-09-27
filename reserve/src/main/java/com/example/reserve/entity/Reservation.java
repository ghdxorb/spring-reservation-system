package com.example.reserve.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // 1. import 추가

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. user 위에 어노테이션 추가
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 3. item 위에 어노테이션 추가
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private LocalDateTime reserveDateTime;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public enum ReservationStatus {
        CONFIRMED, CANCELED
    }

    public Reservation() {}

    public Reservation(Long id, User user, Item item, LocalDateTime reserveDateTime, ReservationStatus status) {
        this.id = id;
        this.user = user;
        this.item = item;
        this.reserveDateTime = reserveDateTime;
        this.status = status;
    }

    // Getter
    public Long getId() { return id; }
    public User getUser() { return user; }
    public Item getItem() { return item; }
    public LocalDateTime getReserveDateTime() { return reserveDateTime; }
    public ReservationStatus getStatus() { return status; }

    // Setter
    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setItem(Item item) { this.item = item; }
    public void setReserveDateTime(LocalDateTime reserveDateTime) { this.reserveDateTime = reserveDateTime; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    // Builder 수동 구현
    public static ReservationBuilder builder() {
        return new ReservationBuilder();
    }

    public static class ReservationBuilder {
        private Long id;
        private User user;
        private Item item;
        private LocalDateTime reserveDateTime;
        private ReservationStatus status;

        public ReservationBuilder id(Long id) { this.id = id; return this; }
        public ReservationBuilder user(User user) { this.user = user; return this; }
        public ReservationBuilder item(Item item) { this.item = item; return this; }
        public ReservationBuilder reserveDateTime(LocalDateTime reserveDateTime) { this.reserveDateTime = reserveDateTime; return this; }
        public ReservationBuilder status(ReservationStatus status) { this.status = status; return this; }

        public Reservation build() {
            return new Reservation(id, user, item, reserveDateTime, status);
        }
    }
}