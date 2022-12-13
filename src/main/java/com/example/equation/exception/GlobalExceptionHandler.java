package com.example.equation.exception;

import com.example.equation.api.ApiResponse;
import com.example.equation.api.RootResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @ExceptionHandler({UserInputParameterException.class, ArithmeticException.class})
    public ResponseEntity<ApiResponse<RootResponse>> handleInputParameterException(Exception ex) {
        logger.warning(ex.getLocalizedMessage());
        ApiResponse<RootResponse> response = new ApiResponse<>();
        response.setDebugMessage("successful request");
        response.setStatus(HttpStatus.OK);
        response.setMessage(ex.getLocalizedMessage());
        return ResponseEntity.ok(response);
    }
}