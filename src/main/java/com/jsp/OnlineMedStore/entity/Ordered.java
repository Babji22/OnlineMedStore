package com.jsp.OnlineMedStore.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordered 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	int memberid;
	int drugid;
//	@OneToMany
//	List<Drug> drugs;
	double orderAmount;
	boolean status_order;

}
