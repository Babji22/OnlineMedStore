package com.jsp.OnlineMedStore.DTO;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.OnlineMedStore.Repository.AddressRepository;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Member;

import lombok.Data;

@Data
public class AddressDTO 
{
	private Integer id;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	
}
