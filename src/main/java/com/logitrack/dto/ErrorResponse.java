package com.logitrack.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;
    private String mensaje;
    private LocalDateTime timestamp;
}
