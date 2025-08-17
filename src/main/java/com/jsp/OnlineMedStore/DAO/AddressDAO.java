package com.jsp.OnlineMedStore.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.OnlineMedStore.Repository.AddressRepository;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Member;

@Component
public class AddressDAO 
{
	@Autowired
	AddressRepository addressRepository;
	
	public Address saveAddress(Address address)
	{
		return addressRepository.save(address);
	}
	
	public Address updateAddress(Address address)
	{
		for (Address address1 : addressRepository.findAll()) {
			if(address1.getId()==address.getId())
			{
				return addressRepository.save(address);
			}
		}
		return null;
	}
	
	public Address deleteAddress(int id)
	{
		for (Address address1 : addressRepository.findAll()) {
			if(address1.getId()==id)
			{
				addressRepository.deleteById(id);
				return address1;
			}
		}
		return null;
	}
	
	
	public Address findAddress(int id)
	{
		Optional<Address> address=addressRepository.findById(id);
			if(address.isPresent())
			{
				return address.get();
			}
		return null;
	}
	
public List<Address> findAllAddress() {
		
		return addressRepository.findAll();
		
	}
	
}
