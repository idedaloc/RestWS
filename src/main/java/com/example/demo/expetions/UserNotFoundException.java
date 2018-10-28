package com.example.demo.expetions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * @param arg0
	 */
	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
