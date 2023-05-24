package com.sereda.ecommerce_project.exception.handler;

import com.sereda.ecommerce_project.dto.ErrorResponse;
import com.sereda.ecommerce_project.exception.RecordNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class StoreExceptionHandler {
    @ExceptionHandler({RecordNotFoundException.class})
    private ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException e) {
        log.warn("Record {} not found with id {}", e.getClazz().getName(), e.getId());

        ErrorResponse response = new ErrorResponse();
        response.setCode("record.not.found");
        response.setMessage(String.format("%s not found by id %s", e.getClazz().getName(), e.getId()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
