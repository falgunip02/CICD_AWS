package com.mynotes.dto;

import java.time.LocalDateTime;

// eshko update nhi kar sakti hu
public record ErrorResponseDto(
        String message,
        int status,
        String error,
        LocalDateTime time,
        String path) {
}
