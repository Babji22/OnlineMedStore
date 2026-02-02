package com.jsp.OnlineMedStore.Util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponce 
{
	
	private int status;
	private String message;
	private LocalDateTime datatime;
	private Object data;
}
