package com.jsp.OnlineMedStore.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jsp.OnlineMedStore.Repository.OrderRepository;
import com.jsp.OnlineMedStore.Service.OrderService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Ordered;

@Component
public class OrderDAO 
{
	@Autowired
	OrderRepository orderRepository;
	
	public Ordered saveOrder(Ordered ordered)
	{
		return orderRepository.save(ordered);
	}

//	@DeleteMapping("/deleteCartDrug")
	public Ordered deleteCartDrug(int cartid)
	{
		Optional<Ordered> cartdatafound=orderRepository.findById(cartid);
		if(cartdatafound.isPresent())
		{
			orderRepository.deleteById(cartid);
			Ordered deletedcartdata=cartdatafound.get();
			return deletedcartdata;
		}
		return null;
	}
	
	public List<Ordered> findOrders() {
		
		return orderRepository.findAll();
	}
}
