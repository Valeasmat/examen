package com.everis.examen.exception;


import lombok.Getter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
public class SaveErrorException extends Exception{
    private String message;
    private String dateTime;
    public SaveErrorException(String message) {
        this.message = message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        this.dateTime = formatter.format(Instant.now());

    }
}
