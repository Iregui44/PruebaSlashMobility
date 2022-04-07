package com.slashmobility.UserProoviders.infraestructure.config;

import com.slashmobility.UserProoviders.domain.exception.EntityExistException;
import com.slashmobility.UserProoviders.domain.exception.EntityNoExistException;
import com.slashmobility.UserProoviders.infraestructure.type.DefaultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingControllerAdvice extends ResponseEntityExceptionHandler {

    public ExceptionHandlingControllerAdvice() {
    }

    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<Object> databaseError(Exception e, WebRequest request) {
        DefaultResponse body = new DefaultResponse("It was not possible to save the data");
        log.error(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EntityNoExistException.class, EntityExistException.class})
    public ResponseEntity<Object> databaseErrorEntity(Exception e, WebRequest request) {
        DefaultResponse body = new DefaultResponse(e.getMessage());
        log.info(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
