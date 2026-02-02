package com.jsp.OnlineMedStore.Exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.OnlineMedStore.Util.SuccessResponce;

@RestControllerAdvice
public class OnlineMedStoreExceptionHandler 
{
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponce> sqlICVE(SQLIntegrityConstraintViolationException e)
	{
		SuccessResponce data=SuccessResponce.builder().status(HttpStatus.BAD_REQUEST.value()).message("Enter valid email").datatime(LocalDateTime.now()).data(e.getMessage()).build();
		return new ResponseEntity<SuccessResponce>(data, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<SuccessResponce> ResourceAlreadyExist(ResourceAlreadyExistException e)
	{
		SuccessResponce data=SuccessResponce.builder().status(HttpStatus.FOUND.value()).message("Resource Already Exists").datatime(LocalDateTime.now()).data(e.getMessage()).build();
		return new ResponseEntity<SuccessResponce>(data, HttpStatus.FOUND);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<SuccessResponce> Notfound(NotFoundException e)
	{
		SuccessResponce data=SuccessResponce.builder().status(HttpStatus.NOT_FOUND.value()).message("you can't perform this operation").datatime(LocalDateTime.now()).data(e.getMessage()).build();
		return new ResponseEntity<SuccessResponce>(data,HttpStatus.NOT_FOUND);
	}
}


