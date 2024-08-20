package com.jboss.migration.kitchensink_migration.exception;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    void testHandleValidationException_UniqueEmailViolation() {
        ValidationException ex = new ValidationException("Unique Email Violation");

        ResponseEntity<?> response = globalExceptionHandler.handleValidationException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(Collections.singletonMap("email", "Email taken"), response.getBody());
    }

    @Test
    void testHandleValidationException_OtherValidationException() {
        ValidationException ex = new ValidationException("Other Error");

        ResponseEntity<?> response = globalExceptionHandler.handleValidationException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Other Error", response.getBody());
    }

    @Test
    void testHandleGlobalException() {
        Exception ex = new Exception("Unexpected error");

        ResponseEntity<String> response = globalExceptionHandler.handleGlobalException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred: Unexpected error", response.getBody());
    }
}
