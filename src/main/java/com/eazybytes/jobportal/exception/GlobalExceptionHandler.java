package com.eazybytes.jobportal.exception;

import com.eazybytes.jobportal.Dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    // Dto Validation exception method
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        fieldErrorList.forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors); // 400 Status code

    }

    // Params Validation Error Exception
    // Also used for PathVariable Validation Exception
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String,String>> handleException(HandlerMethodValidationException exception){
        Map<String,String> errors = new HashMap<>();
        List<ParameterValidationResult> results = exception.getParameterValidationResults();
        results.forEach(result->{
            String paramName = result.getMethodParameter().getParameterName();

            String combinedMessages = result.getResolvableErrors()
                    .stream()
                    .map(error->error.getDefaultMessage())
                    .collect(Collectors.joining(","));
            errors.put(paramName,combinedMessages);
        });

        return ResponseEntity.badRequest().body(errors);

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
