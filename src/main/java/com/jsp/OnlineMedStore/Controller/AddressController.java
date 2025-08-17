package com.jsp.OnlineMedStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jsp.OnlineMedStore.Service.AddressService;

@Controller
public class AddressController 
{
	@Autowired
	AddressService addressService;
	
	
}
