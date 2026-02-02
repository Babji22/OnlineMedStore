package com.jsp.OnlineMedStore.Service;

import java.rmi.NotBoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlineMedStore.DTO.AddressDTO;
import com.jsp.OnlineMedStore.DTO.MemberDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Repository.AddressRepository;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Member;

@Service
public class AddressService 
{

	@Autowired
	AddressRepository addressRepository;
	
	public Address saveAddress(AddressDTO addressDTO)
	{
		return addressRepository.save(fromDTOToEntity(addressDTO));
	}
	
	public AddressDTO updateAddress(AddressDTO addressDTO)
	{
		for (Address address : addressRepository.findAll()) {
			if(address.getId()==addressDTO.getId())
			{
				Address storedAddress=addressRepository.save(fromDTOToEntity(addressDTO));
				return fromEntityToDTO(storedAddress);
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
	
	
	public AddressDTO findAddress(int id)
	{
		Optional<Address> address=addressRepository.findById(id);
		if(address.isPresent())
		{
			return fromEntityToDTO(address.get());
		}
		throw new NotFoundException("Address not found");
	}
	
public List<Address> findAllAddress() {
		
	return addressRepository.findAll();
		
	}

private AddressDTO fromEntityToDTO(Address address)
{
	AddressDTO addressDTO=new AddressDTO();
	addressDTO.setId(address.getId());
	addressDTO.setStreet(address.getStreet());
	addressDTO.setCity(address.getCity());
	addressDTO.setState(address.getState());
	addressDTO.setCountry(address.getCountry());
	addressDTO.setPinCode(address.getPinCode());
	
	return addressDTO;
}

private Address fromDTOToEntity(AddressDTO addressDTO)
{
	Address address=new Address();
	address.setId(addressDTO.getId());
	address.setStreet(addressDTO.getStreet());
	address.setCity(addressDTO.getCity());
	address.setState(addressDTO.getState());
	address.setCountry(addressDTO.getCountry());
	address.setPinCode(addressDTO.getPinCode());
	return address;
}
	
}
