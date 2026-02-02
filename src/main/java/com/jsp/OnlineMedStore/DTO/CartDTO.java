package com.jsp.OnlineMedStore.DTO;

import lombok.Data;

@Data
public class CartDTO {

	private Integer cartId;
	private Integer memberId;
	private Integer drugId;
	private String drugName;
	private Integer quantity;
	private Double price;
}
