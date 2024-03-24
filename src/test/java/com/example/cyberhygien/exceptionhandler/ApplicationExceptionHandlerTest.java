package com.example.cyberhygien.exceptionhandler;

import com.example.cyberhygien.GlobalExceptionHandler.ApplicationExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationExceptionHandlerTest {

    private final ApplicationExceptionHandler exceptionHandler = new ApplicationExceptionHandler();



    @Test
    public void testHandleEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Entity not found");
        ResponseEntity<String> responseEntity = exceptionHandler.handleEntityNotFoundException(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Entity not found: Entity not found", responseEntity.getBody());
    }

    @Test
    public void testHandleDataAccessException() {
        DataAccessException exception = new DataAccessException("Failed to fetch element from the database") {};
        ResponseEntity<Object> responseEntity = exceptionHandler.handleDataAccessException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to fetch element from the database", responseEntity.getBody());
    }

    @Test
    public void testHandleNoSuchElementException() {
        NoSuchElementException exception = new NoSuchElementException("Resource not found");
        ResponseEntity<String> responseEntity = exceptionHandler.handleNoSuchElementException(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found", responseEntity.getBody());
    }

    @Test
    public void testHandleException() {
        Exception exception = new Exception("An error occurred");
        ResponseEntity<String> responseEntity = exceptionHandler.handleException(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred: An error occurred", responseEntity.getBody());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");
        ResponseEntity<String> responseEntity = exceptionHandler.handleIllegalArgumentException(exception);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid argument", responseEntity.getBody());
    }
}
