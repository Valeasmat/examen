package com.everis.examen.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ErrorDetail {
    private String message;
    private String dateTime;
}
