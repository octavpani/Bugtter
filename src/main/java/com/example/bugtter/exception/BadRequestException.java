package com.example.bugtter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

}
