package com.eazybytes.jobportal.Dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDto(String apiPath, HttpStatus errorCode, String errorMessage,
                           LocalDateTime errorTime) {
}
