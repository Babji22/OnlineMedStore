package com.jsp.OnlineMedStore.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException
{
	String message;
}
