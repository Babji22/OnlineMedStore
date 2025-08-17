package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlineMedStore.DAO.AddressDAO;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Member;

@Service
public class AddressService 
{

	@Autowired
	AddressDAO addressDAO;
	
	public ResponseEntity<SuccessResponce> saveAddress(Address address)
	{
		SuccessResponce save=SuccessResponce.builder().status(HttpStatus.CREATED.value()).datatime(LocalDateTime.now()).message("Members Address details saved").data(addressDAO.saveAddress(address)).build();
		return new ResponseEntity<SuccessResponce>(save, HttpStatus.CREATED);
	}

	public ResponseEntity<SuccessResponce> updateAddress(Address address) {
		SuccessResponce update=SuccessResponce.builder().status(HttpStatus.ACCEPTED.value()).datatime(LocalDateTime.now()).message("Address details updated").data(addressDAO.updateAddress(address)).build();
		return new ResponseEntity<SuccessResponce>(update, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<SuccessResponce> deleteAddress(int id) {
		SuccessResponce delete=SuccessResponce.builder().status(HttpStatus.OK.value()).datatime(LocalDateTime.now()).message("Address details deleted").data(addressDAO.deleteAddress(id)).build();
		return new ResponseEntity<SuccessResponce>(delete, HttpStatus.OK);
	}

	public ResponseEntity<SuccessResponce> findAddress(int id) 
	{
		SuccessResponce find=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Address details found").data(addressDAO.findAddress(id)).build();
		return new ResponseEntity<SuccessResponce>(find, HttpStatus.FOUND);
	}
	
	public ResponseEntity<SuccessResponce> findAllAddress() 
	{
		SuccessResponce findall=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(addressDAO.findAllAddress()).build();
		return new ResponseEntity<SuccessResponce>(findall, HttpStatus.FOUND);
	}
	
}
