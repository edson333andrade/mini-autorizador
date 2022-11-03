package br.com.vr.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ViolationException extends RuntimeException{
   private Object body;
   private HttpStatus httpStatus;

    public ViolationException(Object body, HttpStatus httpStatus){
        super();
        this.body = body;
        this.httpStatus = httpStatus;

    }
}
