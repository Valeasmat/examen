package com.everis.examen.controller.advice;


import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.util.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetail> saveErrorException(SaveErrorException exception){
        return new ResponseEntity<>(new ErrorDetail(exception.getMessage(), exception.getDateTime()), HttpStatus.OK);
    }
}
