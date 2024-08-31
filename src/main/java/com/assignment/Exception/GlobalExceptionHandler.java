package com.assignment.Exception;

import com.assignment.Utils.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        log.error("MethodArgumentNotValidException : {}", errors);
        ResponseDTO response = ResponseDTO.customResponseDTO(
                HttpStatus.BAD_REQUEST,
                "One or more fields missing or null",
                errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle JSON parsing exceptions
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage;
        if (Objects.requireNonNull(ex.getRootCause()).getMessage().contains("not one of the values accepted for Enum class")){
            errorMessage = "Choose (Packet, Box, Unit) for packingType.";
        }else {
            errorMessage = "Invalid input: " + ex.getRootCause().getMessage();
        }
        log.error("HttpMessageNotReadableException : {}", errorMessage);
        ResponseDTO response = ResponseDTO.customResponseDTO(
                HttpStatus.BAD_REQUEST,
                "Invalid input data",
                errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
