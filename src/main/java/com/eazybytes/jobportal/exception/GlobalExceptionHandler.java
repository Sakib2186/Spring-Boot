package com.eazybytes.jobportal.exception;

import com.eazybytes.jobportal.Dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // if NullPointerException only hadnles nullpointer
    public ResponseEntity<ExceptionDto> handleException(Exception exception, WebRequest webRequest){
        ExceptionDto errorResponseDto = new ExceptionDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(NullPointerException.class) // if NullPointerException only hadnles nullpointer
    public ResponseEntity<ExceptionDto> handleNullPointerException(Exception exception, WebRequest webRequest){
        ExceptionDto errorResponseDto = new ExceptionDto(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                "A NullPointerException occures due to: "+exception.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
