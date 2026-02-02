package com.jsp.OnlineMedStore.DTO;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class DrugDTO 
{
	private Integer id;
	private String name;
	private String company;
	private String type;
	private Double price;
	private Integer quantity;
	private Integer rating;
//	private boolean banned;
}
