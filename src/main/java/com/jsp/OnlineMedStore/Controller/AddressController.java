package com.jsp.OnlineMedStore.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jsp.OnlineMedStore.DTO.AddressDTO;
import com.jsp.OnlineMedStore.Service.AddressService;
import com.jsp.OnlineMedStore.entity.Address;

@Controller
public class AddressController 
{
	@Autowired
	AddressService addressService;
	
	public Address saveAddress(AddressDTO address)
	{
		return addressService.saveAddress(address);
	}
	
	public AddressDTO updateAddress(AddressDTO address)
	{
		return addressService.updateAddress(address);
	}
	
	public void deleteAddress(int id)
	{
		addressService.deleteAddress(id);
	}
	
	
	public AddressDTO findAddress(int id)
	{
		return addressService.findAddress(id);
	}
	
public List<Address> findAllAddress() {
		
		return addressService.findAllAddress();
		
	}
	
}
