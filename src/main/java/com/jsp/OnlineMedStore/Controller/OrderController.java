package com.jsp.OnlineMedStore.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.Service.OrderService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@Controller
@RequestMapping("/order")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/save")
	public String saveOrder(Ordered ordered)
	{
		orderService.saveOrder(ordered);
		return "home";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("search") String value,Model model)
	{
		System.out.println(value);
		if(value=="")
		{
			return "redirect:/order/allorders"; 
		}
		else
		{
		ResponseEntity<SuccessResponce> response=orderService.findOrders();
		List<Ordered> allorders=(List<Ordered>) response.getBody().getData();
		ArrayList<Ordered> Orderdetails=new ArrayList<Ordered>();
//		boolean ban = false;
//		if(value.equalsIgnoreCase("true"))
//		{
//			ban=true;
//		}
//		else if(value.equalsIgnoreCase("false"))
//		{
//			ban=false;
//		}
		
		for (Ordered order : allorders) {
			
			if((order.getMemberid()==Integer.parseInt(value)) || (order.getDrugid()==Integer.parseInt(value)) || (order.getOrderAmount()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
			{
				Orderdetails.add(order);
			}
		}
		model.addAttribute("allorders", Orderdetails);
		return "AdminOrders";
		}
	}
	
	
	@RequestMapping("/memberordersearch/{id}")
	public String memberordersearch(@RequestParam("search") String value,@PathVariable("id") int memberid,Model model)
	{
		System.out.println(value);
		if(value=="")
		{
			return "redirect:/member/orderdata/"+memberid+"/"+1; 
		}
		else
		{
			List<Drug> allorders=orderService.getMemberOrdersDetails(memberid);
		
		ArrayList<Drug> Orderdetails=new ArrayList<Drug>();
		
		for (Drug drug : allorders) {
			
			if((drug.getName().equalsIgnoreCase(value)) || (drug.getCompany().equalsIgnoreCase(value)) || (drug.getType().equalsIgnoreCase(value)))// || (drug.getQuantity()==Integer.parseInt(value)) || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))
			{
				Orderdetails.add(drug);
			}
		}
		model.addAttribute("orderdrugs", Orderdetails);
		model.addAttribute("memberid", memberid);
		return "orderdetails";
		}
	}
	
	@RequestMapping("/membercartsearch/{id}")
	public String membercartsearch(@RequestParam("search") String value,@PathVariable("id") int memberid,Model model)
	{
		System.out.println(value);
		if(value=="")
		{
			return "redirect:/member/cartdata/"+memberid+"/"+1; 
		}
		else
		{
			List<Drug> allcartdrugs=orderService.getMemberCartDetails(memberid);
//			System.out.println(allcartdrugs);
		ArrayList<Drug> cartdrugs=new ArrayList<Drug>();
		
		for (Drug drug : allcartdrugs) {
			System.out.println(drug);
			if((drug.getName().equalsIgnoreCase(value)) || (drug.getCompany().equalsIgnoreCase(value)) || (drug.getType().equalsIgnoreCase(value)))// || drug.getQuantity()==Integer.parseInt(value))// || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))
			{
				cartdrugs.add(drug);
			}
		}
		model.addAttribute("alldrugs", cartdrugs);
		model.addAttribute("memberid", memberid);
		return "cartdetails";
		}
	}
	
	@PostMapping("/deleteCartDrug/{drugid}/{member}")
	public String deleteCartDrug(@PathVariable("drugid") int drugid,@PathVariable("member") int memberid)
	{
		System.out.println(drugid);
		orderService.deleteCartDrug(drugid,memberid);
		return "redirect:/member/cartdata/"+memberid+"/"+1;
	}
	
	@PostMapping("/drugorder")
	public ResponseEntity<SuccessResponce> drugOrder(@RequestParam int custid, @RequestParam List<List<Integer>> drugname)
	{
		return orderService.ordersDrug(custid, drugname);
	}
	
	@RequestMapping("/allorders")
	public String allMembersData(Model model)
	{
		ResponseEntity<SuccessResponce> response=orderService.findOrders();
		List<Ordered> allorders=(List<Ordered>) response.getBody().getData();
		model.addAttribute("allorders", allorders);
		System.out.println(allorders);
		return "AdminOrders";
	}
}
