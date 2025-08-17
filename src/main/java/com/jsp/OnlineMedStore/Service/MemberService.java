package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.OnlineMedStore.DAO.MemberDAO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@Service
public class MemberService 
{
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	DrugService drugService;
	
	public ResponseEntity<SuccessResponce> saveMember(Member member)
	{
		SuccessResponce save=SuccessResponce.builder().status(HttpStatus.CREATED.value()).datatime(LocalDateTime.now()).message("Members details saved").data(memberDAO.saveMember(member)).build();
		return new ResponseEntity<SuccessResponce>(save, HttpStatus.CREATED);
	}

	public ResponseEntity<SuccessResponce> updateMember(Member member) {
		SuccessResponce update=SuccessResponce.builder().status(HttpStatus.ACCEPTED.value()).datatime(LocalDateTime.now()).message("Members details saved").data(memberDAO.updateMember(member)).build();
		return new ResponseEntity<SuccessResponce>(update, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<SuccessResponce> deleteMember(int id) {
		SuccessResponce delete=SuccessResponce.builder().status(HttpStatus.OK.value()).datatime(LocalDateTime.now()).message("Members details deleted").data(memberDAO.deleteMember(id)).build();
		return new ResponseEntity<SuccessResponce>(delete, HttpStatus.OK);
	}

	public ResponseEntity<SuccessResponce> findMember(int id) 
	{
//		SuccessResponce find=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(memberDAO.findMember(id)).build();
//		return new ResponseEntity<SuccessResponce>(find, HttpStatus.FOUND);
//	}
//	
//	
//	@GetMapping("/getmember/{id}")
//	public String getMember(@PathVariable("id")int id, Model model)
//	{
		Member memberdata=memberDAO.findMember(id);
		if(memberdata.getId()!=0)
		{
			Address addid=memberdata.getAddress();
			if(addid==null)
			{
//				ResponseEntity<SuccessResponce> responseadd=addressService.findAddress(id);
//				Address addressdata=(Address) responseadd.getBody().getData();
//				model.addAttribute("addressdata", addid);
				addid=new Address();
				memberdata.setAddress(addid);
				//model.addAttribute("addressdata", memberdata.getAddress());
			}
//			System.out.println(addid.getId());
//			System.out.println(addid.getStreet());
		}
//		}
		
//		model.addAttribute("memberdata", memberdata);
//		System.out.println("hii");
		SuccessResponce find=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(memberdata).build();
		return new ResponseEntity<SuccessResponce>(find, HttpStatus.FOUND);
	
		
//		return memberdata;
		
	}
	
	
	public String addToCartService(int drugid ,int memberid)
	{
//		ResponseEntity<SuccessResponce> responsedrug=drugService.findById(drugid);
		List<Drug>  cartdrug= orderService.getMemberCartDetails(memberid);
		boolean status=false;
		for(int i=0;i<cartdrug.size();i++)
		{
			if(cartdrug.get(i).getId()==drugid)
			{
				status=true;
				break;
			}
		}
		if(status==false)
		{
		Ordered cart=new Ordered();
		cart.setMemberid(memberid);
		cart.setDrugid(drugid);
		cart.setStatus_order(false);
		orderService.saveOrder(cart);
//		System.out.println(memberid);
		}
		return "home2";
		
//		else
//		{
//			return null
//		}
	}
	
	
	public List<Integer> AdmindashboardPageService(int memberid)
	{
		
		ResponseEntity<SuccessResponce> responseOrders=orderService.findOrders();
		List<Ordered> allordersdata=(List<Ordered>) responseOrders.getBody().getData();
		ArrayList<Integer> drugsdata=new ArrayList<Integer>();
		int cartcount=0;
		int ordercount=0;
		for (int mid=0;mid<allordersdata.size();mid++) 
		{
			if(allordersdata.get(mid).getMemberid()==memberid)
			{
				if(allordersdata.get(mid).isStatus_order()==false)
				{
					ResponseEntity<SuccessResponce> responsedrug=drugService.findById(allordersdata.get(mid).getDrugid());
					Drug alldrugdata=(Drug) responsedrug.getBody().getData();
					cartcount++;
				}
				else
				{
					ResponseEntity<SuccessResponce> responsedrug=drugService.findById(allordersdata.get(mid).getDrugid());
					Ordered alldrugdata=(Ordered) responsedrug.getBody().getData();
					ordercount++;
				}
			}
		}
		drugsdata.add(cartcount);
		drugsdata.add(ordercount);
		

		
//		ResponseEntity<SuccessResponce> responsedrugs=drugService.findAllDrugs();
//		List<Drug> alldrugs=(List<Drug>) responsedrugs.getBody().getData();
//		model.addAttribute("alldrugs", alldrugs.size());
//		ResponseEntity<SuccessResponce> responsemembers=memberService.findMembers();
//		List<Drug> allmembers=(List<Drug>) responsemembers.getBody().getData();
//		model.addAttribute("allmembers", allmembers.size());
//		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
//		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
//		model.addAttribute("alldrugs", alldrugs.size());
		return drugsdata;
	}
	
	
	
	
	public ResponseEntity<SuccessResponce> findMembers() 
	{
		SuccessResponce findall=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(memberDAO.findMembers()).build();
		return new ResponseEntity<SuccessResponce>(findall, HttpStatus.FOUND);
	}
	
	public ResponseEntity<SuccessResponce> MemberLogin(String email,String password)
	{
		if (memberDAO.loginByEmail(email)!=null)
		{
			if(memberDAO.loginByPassword(password)!=null)
			{
				if(memberDAO.loginByPassword(password).getEmail().equals(email))
				{
					SuccessResponce login=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).data(memberDAO.loginByEmail(email)).message("Admin details found").build();
					return new ResponseEntity<SuccessResponce>(login, HttpStatus.FOUND);
				}
				else
				{
					throw new NotFoundException("Member "+password+" is not found");
				}
			}
			else
			{
				throw new NotFoundException("member "+password+" is not found");
			}
		} 
		else 
		{
			throw new NotFoundException("member "+email+" is not found");
		}
	}
}
