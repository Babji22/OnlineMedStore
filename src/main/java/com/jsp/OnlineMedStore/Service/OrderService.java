package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.DTO.MemberDTO;
import com.jsp.OnlineMedStore.DTO.OrderDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Repository.DrugRepository;
import com.jsp.OnlineMedStore.Repository.MemberRepository;
import com.jsp.OnlineMedStore.Repository.OrderRepository;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

import jakarta.transaction.Transactional;

@Service
public class OrderService 
{
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	DrugRepository drugRepository;

	@Autowired
	DrugService drugService;

	@Transactional
	public ResponseEntity<String> saveOrder(List<OrderDTO> orderDTOList)
	{
		//SuccessResponce save=SuccessResponce.builder().status(HttpStatus.CREATED.value()).datatime(LocalDateTime.now()).message().data(orderDAO.saveOrder(ordered)).build();
		List<Ordered> orderlist=new ArrayList<Ordered>();
		for(OrderDTO orderDTO:orderDTOList)
		{
			orderlist.add(fromDTOToEntity(orderDTO));
		}
		orderRepository.saveAll(orderlist);
		return new ResponseEntity<String>("order details saved", HttpStatus.CREATED);
	}

	
	

	public ResponseEntity<List<OrderDTO>> GetAllOrders() {
		List<OrderDTO> orderlist=new ArrayList<>();
		for (Ordered order : orderRepository.findAll()) 
		{

				orderlist.add(fromEntityToDTO(order));

		}
		
		return new ResponseEntity<List<OrderDTO>>(orderlist, HttpStatus.FOUND);
	}
	
	public ResponseEntity<String> deleteCartDrug(int drugid,int memberid) {
		int cartid=0;
	    List<OrderDTO>ordersdata=GetAllOrders().getBody();
	    for (OrderDTO order : ordersdata) 
	    {
			if (order.getMemberId()==memberid) {
				{
					cartid=order.getId();
				}
			}
		}
	    
		
		return new ResponseEntity<String>("Drug in cart Deleted Sucessfully", HttpStatus.FOUND);
	}
	
	public List<DrugDTO> getMemberCartDetails(int memberid)
	{
		
		List<OrderDTO> allordersdata=GetAllOrders().getBody();
		ArrayList<DrugDTO> cartdrugs=new ArrayList<DrugDTO>();
		for (int mid=0;mid<allordersdata.size();mid++) 
		{
			if(allordersdata.get(mid).getMemberId()==memberid)
			{
				
				if(allordersdata.get(mid).getOrderStatus()==false)
				{
					
					DrugDTO alldrugdata=drugService.findById(allordersdata.get(mid).getDrugId()).getBody();
					cartdrugs.add(alldrugdata);
				}
			}
		}
		return cartdrugs;
	}
	
	
	public List<DrugDTO> getMemberOrdersDetails(int memberid)
	{
		
		List<OrderDTO> allordersdata=GetAllOrders().getBody();
		ArrayList<DrugDTO> orderdrugs=new ArrayList<DrugDTO>();
		for (int mid=0;mid<allordersdata.size();mid++) 
		{
			if(allordersdata.get(mid).getMemberId()==memberid)
			{
				if(allordersdata.get(mid).getOrderStatus()==true)
				{
					DrugDTO alldrugdata=drugService.findById(allordersdata.get(mid).getDrugId()).getBody();
					alldrugdata.setQuantity((int) (allordersdata.get(mid).getOrderAmount()/alldrugdata.getPrice()));
					orderdrugs.add(alldrugdata);
				}
			}
		}
		return orderdrugs;
	}
	
	
	
	private OrderDTO fromEntityToDTO(Ordered order) {
	    OrderDTO orderDTO = new OrderDTO();
	    
	    orderDTO.setId(order.getId());
	    orderDTO.setMemberId(order.getMemberId());
	    orderDTO.setDrugId(order.getDrugId());
	    orderDTO.setOrderAmount(order.getOrderAmount());
	    orderDTO.setDrugName(order.getDrugName());
	    orderDTO.setOrderStatus(order.getOrderStatus()); 
	    
	    return orderDTO;
	}

	private Ordered fromDTOToEntity(OrderDTO orderDTO) {
	    Ordered order = new Ordered();
	    
	    order.setId(orderDTO.getId());
	    order.setMemberId(orderDTO.getMemberId());
	    order.setDrugId(orderDTO.getDrugId());
	    order.setDrugName(orderDTO.getDrugName());
	    order.setQuantity(orderDTO.getQuantity());
	    order.setOrderAmount(orderDTO.getOrderAmount());
	    order.setOrderStatus(orderDTO.getOrderStatus());
	    
	    return order;
	}



	public ResponseEntity<List<OrderDTO>> getUserOrder(Integer memberId) {
		
		List<OrderDTO> orderList=new ArrayList<OrderDTO>();
		List<Ordered> getOrderList= orderRepository.findAll();
		for(Ordered order : getOrderList)
		{
			if(order.getMemberId()==memberId)
			{
				orderList.add(fromEntityToDTO(order));
			}
		}
		return new ResponseEntity<List<OrderDTO>>(orderList, HttpStatus.FOUND);
	}



}
