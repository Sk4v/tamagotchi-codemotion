package com.objectway.codemotion.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class Response<T> {

	public HttpStatus status = HttpStatus.OK;
	public String message;
	public T data;	
}
