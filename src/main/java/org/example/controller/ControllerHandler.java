package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.dto.ErrorDTO;
import org.example.error.MathException;
import org.example.error.SatelliteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerHandler {


    @ExceptionHandler(value = MathException.class)
    public ResponseEntity<ErrorDTO> mathException(MathException ex) {
        ErrorDTO error = ErrorDTO.builder().type(ex.getClass().toString()).message(ex.getMessage()).build();
        return ResponseEntity.unprocessableEntity().body(error);
    }

    @ExceptionHandler(value = SatelliteException.class)
    public ResponseEntity<ErrorDTO> satelliteException(SatelliteException ex) {
        ErrorDTO error = ErrorDTO.builder().type(ex.getClass().toString()).message(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(error);
    }


}
