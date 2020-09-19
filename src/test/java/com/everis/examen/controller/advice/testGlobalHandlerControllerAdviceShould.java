package com.everis.examen.controller.advice;


import com.everis.examen.exception.ResourceNotFoundException;
import com.everis.examen.exception.SaveErrorException;
import com.everis.examen.util.ErrorDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class testGlobalHandlerControllerAdviceShould {
    @InjectMocks
    private GlobalHandlerControllerAdvice handler;

    @Test
    public void testResourceNotFoundException() {
        assertNotNull(handler.resourceNotFoundException(new ResourceNotFoundException()));
    }

    @Test
    public void testBusinessException() {
        SaveErrorException exception = new SaveErrorException("error");
        ResponseEntity<ErrorDetail> result = handler.saveErrorException(exception);
        assertNotNull(result);
        assertEquals(exception.getMessage(), result.getBody().getMessage());
        assertEquals(exception.getDateTime(), result.getBody().getDateTime());
    }
}
