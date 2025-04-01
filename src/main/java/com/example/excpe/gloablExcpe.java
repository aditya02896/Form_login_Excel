package com.example.excpe;

import com.example.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class gloablExcpe {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto>handelEx(Exception e , WebRequest webRequest){
        ErrorDto errorDto = new ErrorDto(e.getMessage() , new Date(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
