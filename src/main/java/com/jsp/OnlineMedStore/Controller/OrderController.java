package com.jsp.OnlineMedStore.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.DTO.OrderDTO;
import com.jsp.OnlineMedStore.Service.OrderService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/")
	public String saveOrder(@RequestBody List<OrderDTO> orderDTOlist)
	{
		System.out.println(orderDTOlist);
		orderService.saveOrder(orderDTOlist);
		return "home";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("search") String value,Model model)
	{
		if(value=="")
		{
			return "redirect:/order/allorders"; 
		}
		else
		{
		ResponseEntity<List<OrderDTO>> response=orderService.GetAllOrders();
		List<OrderDTO> allorders=(List<OrderDTO>) response.getBody();
		ArrayList<OrderDTO> Orderdetails=new ArrayList<OrderDTO>();
		
		for (OrderDTO orderDTO : allorders) {
			
			if((orderDTO.getMemberId()==Integer.parseInt(value)) || (orderDTO.getDrugId()==Integer.parseInt(value)) || (orderDTO.getOrderAmount()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
			{
				Orderdetails.add(orderDTO);
			}
		}
		model.addAttribute("allorders", Orderdetails);
		return "AdminOrders";
		}
	}
	
	
	@GetMapping("/memberordersearch/{id}")
	public String memberordersearch(@RequestParam("search") String value,@PathVariable("id") int memberid,Model model)
	{
		if(value=="")
		{
			return "redirect:/member/orderdata/"+memberid+"/"+1; 
		}
		else
		{
			List<DrugDTO> allorders=orderService.getMemberOrdersDetails(memberid);
		
			ArrayList<DrugDTO> Orderdetails=new ArrayList<DrugDTO>();
		
			for (DrugDTO drugDTO : allorders) {
			
			if((drugDTO.getName().equalsIgnoreCase(value)) || (drugDTO.getCompany().equalsIgnoreCase(value)) || (drugDTO.getType().equalsIgnoreCase(value)))// || (drug.getQuantity()==Integer.parseInt(value)) || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))
			{
				Orderdetails.add(drugDTO);
			}
		}
		model.addAttribute("orderdrugs", Orderdetails);
		model.addAttribute("memberid", memberid);
		return "orderdetails";
		}
	}
	
	@GetMapping("/membercartsearch/{id}")
	public String membercartsearch(@RequestParam("search") String value,@PathVariable("id") int memberid,Model model)
	{
		if(value=="")
		{
			return "redirect:/member/cartdata/"+memberid+"/"+1; 
		}
		else
		{
			List<DrugDTO> allcartdrugs=orderService.getMemberCartDetails(memberid);
			ArrayList<DrugDTO> cartdrugs=new ArrayList<DrugDTO>();
		
			for (DrugDTO drugDTO : allcartdrugs) 
			{
			if((drugDTO.getName().equalsIgnoreCase(value)) || (drugDTO.getCompany().equalsIgnoreCase(value)) || (drugDTO.getType().equalsIgnoreCase(value)))// || drug.getQuantity()==Integer.parseInt(value))// || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))
			{
				cartdrugs.add(drugDTO);
			}
		}
		model.addAttribute("alldrugs", cartdrugs);
		model.addAttribute("memberid", memberid);
		return "cartdetails";
		}
	}
	
	@DeleteMapping("/deleteCartDrug/{drugid}/{member}")
	public String deleteCartDrug(@PathVariable("drugid") int drugid,@PathVariable("member") int memberid)
	{
		orderService.deleteCartDrug(drugid,memberid);
		return "redirect:/member/cartdata/"+memberid+"/"+1;
	}
	
	@GetMapping("/getorders/{memberId}")
	public List<OrderDTO> getUserOrder(@PathVariable("memberId") Integer memberId)
	{
//		System.out.println(orderService.getUserOrder(memberId).getBody());
		return orderService.getUserOrder(memberId).getBody();
	}
	
	@GetMapping("/allorders")
	public List<OrderDTO> allOrders(Model model)
	{
		ResponseEntity<List<OrderDTO>> response=orderService.GetAllOrders();
		List<OrderDTO> allorders=(List<OrderDTO>) response.getBody();
		return allorders;

	}
}
