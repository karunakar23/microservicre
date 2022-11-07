package com.wecare.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wecare.utility.WecareConstants;
@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(CoachNotFoundException.class)
	public ResponseEntity<ErrorInfo> coachNotFoundExceptionHandler(CoachNotFoundException e){
		ErrorInfo error=new ErrorInfo();
		error.setErrorcode(HttpStatus.BAD_REQUEST.value());
		error.setMsg(e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
	
}
