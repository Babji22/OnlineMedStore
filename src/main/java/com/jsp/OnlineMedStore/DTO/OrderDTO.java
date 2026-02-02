package com.jsp.OnlineMedStore.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class OrderDTO 
{
	private Integer id;
	private Integer memberId;
	private Integer drugId;
	private String drugName;
	private Integer quantity;
	private Double orderAmount;
	private Boolean orderStatus;
	
}
