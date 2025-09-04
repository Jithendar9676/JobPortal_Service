package com.jobsadda.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger log=LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo>gernalException(Exception exception){
		 log.error("Unexpected error occurred", exception);
	     ErrorInfo errorInfo=new ErrorInfo(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now());
	     return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo>validatorExceptionHandler(Exception exception){
		String msg="";
		if(exception instanceof MethodArgumentNotValidException methodException) {
			msg=methodException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
		}else {
			ConstraintViolationException conException=(ConstraintViolationException) exception;
			msg=conException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		}
		ErrorInfo errorInfo=new ErrorInfo(msg,HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
	     return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
	}
}
