package com.example.reserve.dto;

import lombok.*;

public class ItemDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String name;
        private String description;
        private Integer capacity;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String description;
        private Integer capacity;
    }
}