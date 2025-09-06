package com.jobsadda.utility;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobsadda.exception.JobsAddaException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	private Environment environment;
	private static final Logger log=LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo>gernalException(Exception exception ,HttpServletRequest request){
		 log.error("Unexpected error occurred", exception);
	     ErrorInfo errorInfo=new ErrorInfo(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now(),request.getRequestURI());
	     return new ResponseEntity<>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(JobsAddaException.class)
	public ResponseEntity<ErrorInfo>gernalException(JobsAddaException exception,HttpServletRequest request){
		 log.error("Unexpected error occurred", exception);
		 String msg= environment.getProperty(exception.getMessage());
		 HttpStatus status;
		 if("LOGIN_FAILED".equals(exception.getMessage())) {
			 status=HttpStatus.UNAUTHORIZED;
		 }else if("USER_FOUND".equals(exception.getMessage())){
			 status=HttpStatus.CONFLICT;
		 }else {
			 status=HttpStatus.BAD_REQUEST;
		 }
		 
	     ErrorInfo errorInfo=new ErrorInfo(msg,status.value(),LocalDateTime.now(),request.getRequestURI());
	     return new ResponseEntity<>(errorInfo,status);
	}
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo>validatorExceptionHandler(Exception exception,HttpServletRequest request){
		String msg="";
		if(exception instanceof MethodArgumentNotValidException methodException) {
			msg=methodException.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "));
		}else {
			ConstraintViolationException conException=(ConstraintViolationException) exception;
			msg=conException.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
		}
		ErrorInfo errorInfo=new ErrorInfo(msg,HttpStatus.BAD_REQUEST.value(),LocalDateTime.now(),request.getRequestURI());
	     return new ResponseEntity<>(errorInfo,HttpStatus.BAD_REQUEST);
	}
}
