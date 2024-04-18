package com.userimran.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExHandler {

	@ExceptionHandler(value = Exception.class)
	public String exHandler(Exception e) {
		
		return "errorPage";
	}
}
