package com.example.demo.expetions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponceEntityExceptionHandler 
	extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllExeptions(Exception ex, WebRequest request) {
		// TODO Auto-generated method stub
		
		ExceptionResponce eh = new ExceptionResponce(new Date(), ex.getMessage(), 
				request.getDescription(false));
				
		return new ResponseEntity<Object>(eh,HttpStatus.NOT_FOUND );

	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponce er = new ExceptionResponce(new Date(), "Validation Fail", ex.getBindingResult().toString());
		
		return new ResponseEntity<Object>(er,HttpStatus.BAD_REQUEST);
	}

	

}
