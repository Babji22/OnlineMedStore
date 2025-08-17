package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.OnlineMedStore.DAO.DrugDAO;
import com.jsp.OnlineMedStore.DAO.MemberDAO;
import com.jsp.OnlineMedStore.DAO.OrderDAO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@Service
public class OrderService 
{
	@Autowired
	MemberDAO memberDAO;
//	@Autowired
//	Ordered order;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	DrugDAO drugDAO;

	
	public ResponseEntity<SuccessResponce> saveOrder(Ordered ordered)
	{
		SuccessResponce save=SuccessResponce.builder().status(HttpStatus.CREATED.value()).datatime(LocalDateTime.now()).message("order details saved").data(orderDAO.saveOrder(ordered)).build();
		return new ResponseEntity<SuccessResponce>(save, HttpStatus.CREATED);
	}

	
	
	public ResponseEntity<SuccessResponce> ordersDrug(int memberid,List<List<Integer>> ordersarray)
	{
//		Member member=memberDAO.findMember(memid);
		List<Ordered> allcartlist=orderDAO.findOrders();
//		List<Object> druglist=new ArrayList<>();
		System.out.println(ordersarray.size());
		for(Ordered od:allcartlist)
		{
			int c=0;
			if(od.getMemberid()==memberid)
			{
				System.out.println(c++);
				for(int i=0;i<ordersarray.size();i++)
				{
					if((od.getDrugid()==ordersarray.get(i).get(0)) && od.isStatus_order()==false )
					{
						od.setOrderAmount(ordersarray.get(i).get(2));
						od.setStatus_order(true);
						orderDAO.saveOrder(od);
						
						Drug drugid=drugDAO.findById(od.getDrugid());
//						Drug updatedrug=new Drug();
//						System.out.println(drugid);
						
						drugid.setQuantity(drugid.getQuantity()-ordersarray.get(i).get(1));
						drugDAO.editDrug(drugid);
//						System.out.println(drugid.getQuantity());
//						System.out.println(ordersarray.get(i).get(1));
//						System.out.println(drugDAO.addDrug(drugid));
//						;
						
//						druglist.add(od.getId());
//						druglist.add(drugid.getId());
						
						
						break;
					}
				}
				System.out.println("hii");
//				od.setDrugs((Drug)druglist);
			}
			
		}
		
//		List<Drug> druglist=new ArrayList<>();
//		double totalamount=0;
//		if (member!=null)
//		{
//			for(String drug:drugname)
//			{
//				Drug drug2=drugDAO.findByDrugName(drug);
//				if(drug2!=null)
//				{
//					if(drug2.getQuantity()!=0)
//					{
//						totalamount+=drug2.getPrice();
//						druglist.add(drug2);
//					}
//					else {
//						throw new NotFoundException("Drug Out of Stock");
//					}
//				}
//				else
//				{
//					throw new NotFoundException("Drug not Found");
//				}
//				
//				Ordered orders=new Ordered();
//				orders.setMemberid(memid);
//				orders.setOrderAmount(totalamount);
//				orders.setDrugs(druglist);
//				orderDAO.saveOrder(orders);
//				
				SuccessResponce saveorders=SuccessResponce.builder()
						.status(HttpStatus.CREATED.value())
						.datatime(LocalDateTime.now())
//						.data(orderDAO.saveOrder(od))
						.message("Order Placed Successfully")
						.build();
				
				return new ResponseEntity<SuccessResponce>(saveorders, HttpStatus.CREATED);
				
//			}
//		}
//			else {
//				throw new NotFoundException("Not Found");
//			}
//		return null;
		}

	public ResponseEntity<SuccessResponce> findOrders() {
		List<Ordered> orderlist=new ArrayList<>();
		for (Ordered order : orderDAO.findOrders()) 
		{
			if(order.isStatus_order()==true)
			{
				orderlist.add(order);
			}
		}
		
		SuccessResponce findall=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(orderlist).build();
		return new ResponseEntity<SuccessResponce>(findall, HttpStatus.FOUND);
	}
	
	public ResponseEntity<SuccessResponce> deleteCartDrug(int drugid,int memberid) {
		int cartid=0;
	    List<Ordered>ordersdata=orderDAO.findOrders();
	    for (Ordered ordered : ordersdata) 
	    {
			if (ordered.getMemberid()==memberid) {
				if((ordered.getDrugid()==drugid) && ordered.isStatus_order()==false)
				{
					cartid=ordered.getId();
				}
			}
		}
	    
		
		SuccessResponce deletecartdata=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(orderDAO.deleteCartDrug(cartid)).build();
		return new ResponseEntity<SuccessResponce>(deletecartdata, HttpStatus.FOUND);
	}
	
	public List<Drug> getMemberCartDetails(int memberid)
	{
		
//		System.out.println(value);
		List<Ordered> allordersdata=orderDAO.findOrders();
		ArrayList<Drug> cartdrugs=new ArrayList<Drug>();
//		System.out.println(allordersdata.size());
		for (int mid=0;mid<allordersdata.size();mid++) 
		{
			if(allordersdata.get(mid).getMemberid()==memberid)
			{
				
				if(allordersdata.get(mid).isStatus_order()==false)
				{
					
					Drug alldrugdata=drugDAO.findById(allordersdata.get(mid).getDrugid());
//					System.out.println(alldrugdata);
					cartdrugs.add(alldrugdata);
				}
			}
		}
//		if(value==0)
//		{
//		model.addAttribute("cartdrugs", cartdrugs);
//		System.out.println(allordersdata);
//		System.out.println(cartdrugs);
		return cartdrugs;
//		}
//		else
//		{
//			model.addAttribute("alldrugs", cartdrugs);
//			return "cartdetails";
//		}
	}
	
	
	public List<Drug> getMemberOrdersDetails(int memberid)
	{
		
//		System.out.println(value);
		List<Ordered> allordersdata=orderDAO.findOrders();
		ArrayList<Drug> orderdrugs=new ArrayList<Drug>();
		for (int mid=0;mid<allordersdata.size();mid++) 
		{
			if(allordersdata.get(mid).getMemberid()==memberid)
			{
				if(allordersdata.get(mid).isStatus_order()==true)
				{
					Drug alldrugdata=drugDAO.findById(allordersdata.get(mid).getDrugid());
					alldrugdata.setQuantity((int) (allordersdata.get(mid).getOrderAmount()/alldrugdata.getPrice()));
					orderdrugs.add(alldrugdata);
				}
			}
		}
//		if(value==0)
//		{
//		model.addAttribute("cartdrugs", cartdrugs);
		System.out.println(orderdrugs);
		System.out.println("hii");
		return orderdrugs;
//		}
//		else
//		{
//			model.addAttribute("alldrugs", cartdrugs);
//			return "cartdetails";
//		}
	}
}
