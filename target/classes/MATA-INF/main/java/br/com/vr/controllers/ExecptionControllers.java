package br.com.vr.controllers;

import br.com.vr.exception.ViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ApiIgnore
@RestController
@ControllerAdvice
public class ExecptionControllers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ViolationException.class)
    public ResponseEntity handleConstraintViolationException(ViolationException e, HttpServletRequest request) throws IOException {
        return new ResponseEntity(e.getBody(), e.getHttpStatus());
    }
}
