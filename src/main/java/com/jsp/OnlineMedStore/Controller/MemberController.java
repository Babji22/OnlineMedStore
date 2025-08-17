package com.jsp.OnlineMedStore.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.Service.AddressService;
import com.jsp.OnlineMedStore.Service.DrugService;
import com.jsp.OnlineMedStore.Service.MemberService;
import com.jsp.OnlineMedStore.Service.OrderService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@Controller
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	MemberService memberService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	DrugService drugService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/Registeration")
	public String MembersPage()
	{
	
		return "Register";
	}
	
	@RequestMapping("/Register")
	public String MembershomePage()
	{
	
		return "path";
	}
	
	@RequestMapping("/name")
	public String Memberaccount(@RequestParam("name") int id, Model model)
	{
		ResponseEntity<SuccessResponce> response=memberService.findMember(id);
		Member memberlogin=(Member) response.getBody().getData();
		model.addAttribute("memberlogin", memberlogin);
		return "member";
	}
	
	
	
	
	@PostMapping("/register")
	public String saveMember(Member member)
	{
		memberService.saveMember(member);
		return "redirect:/Medicine";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("search") String value,Model model)
	{
		System.out.println(value);
		ResponseEntity<SuccessResponce> response=memberService.findMembers();
		List<Member> allmembers=(List<Member>) response.getBody().getData();
		ArrayList<Member> memberdetails=new ArrayList<Member>();
		for (Member member : allmembers) {
			if((member.getEmail().equalsIgnoreCase(value)) || (member.getGender().equalsIgnoreCase(value)) || member.getName().equalsIgnoreCase(value) || member.getAddress().getCountry().equalsIgnoreCase(value) || member.getAddress().getPincode().equalsIgnoreCase(value) || member.getAddress().getState().equalsIgnoreCase(value) || member.getAddress().getStreet().equalsIgnoreCase(value))
			{
				memberdetails.add(member);
			}
		}
		model.addAttribute("allmembers", memberdetails);
		return "memberslist";
	}
	
	
	@GetMapping("/getmember/{id}/{val}")
	public String findMember(@PathVariable("id") int id,@PathVariable("val") int val, Model model)
	{
		ResponseEntity<SuccessResponce> response=memberService.findMember(id);
		Member memberdata=(Member) response.getBody().getData();
		model.addAttribute("memberdata", memberdata);
		model.addAttribute("val", val);
		return "update";
		
	}
	
	@RequestMapping("/getmember/updatemember/{id}/{val}")
	public String updateMember(@PathVariable("id") int mid,@PathVariable("val") int val, Member member, Address address)
	{
		if(val==0)
		{
		System.out.println(mid);
		member.setAddress(address);
		memberService.updateMember(member);
		return "redirect:/member/findall";
		}
		else
		{
			System.out.println(mid);
			member.setAddress(address);
			memberService.updateMember(member);
			return "redirect:/member/getmember/"+mid+"/"+val;
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") int id)
	{
		memberService.deleteMember(id);
		return "redirect:/member/findall";
	}
	
	@GetMapping("/find")
	public ResponseEntity<SuccessResponce> findMember(int id)
	{
		return memberService.findMember(id);
	}
	
	@GetMapping("/findall")
	public String findMembers(Model model)
	{
		
		ResponseEntity<SuccessResponce> response=memberService.findMembers();
		List<Member> allmembers=(List<Member>) response.getBody().getData();
		model.addAttribute("allmembers", allmembers);
//		model.addAttribute("adminid", adminid);
		return "memberslist";
	}
	
	@RequestMapping("/dealspage/{memberid}")
	public String DealsPage(@PathVariable("memberid") int memberid, Model model)
	{
		System.out.println(memberid);
		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
		model.addAttribute("alldrugs", alldrugs);
		model.addAttribute("member",memberid);
		return "deals";
	}
	
	@RequestMapping("/dashboard/{memberid}")
	public String AdmindashboardPage(@PathVariable("memberid") int memberid,Model model)
	{
		System.out.println(memberid);
		List<Integer> drugsdata=memberService.AdmindashboardPageService(memberid);
		model.addAttribute("allmembers", drugsdata.get(0));
		model.addAttribute("alldrugs", drugsdata.get(1));
		return "AdminDashboard";
	}
	
	@GetMapping("/login")
	public String loginAdmin(String email,String password,Model model)
	{

		ResponseEntity<SuccessResponce> response=memberService.MemberLogin(email, password);
		Member memberlogin=(Member) response.getBody().getData();
		model.addAttribute("memberlogin", memberlogin);
//		ResponseEntity<SuccessResponce> responsedrug=drugService.findAllDrugs();
//		List<Drug> alldrugs=(List<Drug>) responsedrug.getBody().getData();
//		model.addAttribute("alldrugs", alldrugs);
		return "home2";
	}
	
	@PostMapping("/dealspage/addcart")
	public String addToCart(@RequestParam("drugid") int drugid , @RequestParam("memberid") int memberid)
	{
		System.out.println(drugid);
		memberService.addToCartService(drugid, memberid);
		return "redirect:"+memberid;
	}
	
	
	
	@RequestMapping("/logout")
	public String logout()
	{
		return "redirect:/Medicine";
	}
	
	
	
	@GetMapping("/cartdetails")
	public String getMemberCartDetails(@RequestParam("memberid") int memberid,@RequestParam("value") int value ,Model model)
	{
		
		if(value==0)
		{
		model.addAttribute("cartdrugs", orderService.getMemberCartDetails(memberid));
		return "Cart";
		}
		else
		{
			model.addAttribute("alldrugs", orderService.getMemberCartDetails(memberid));
			model.addAttribute("memberid", memberid);
			return "cartdetails";
		}
	}
	
	@PostMapping("/order")
	public String OrderData(@RequestParam("id") int memberid, @RequestParam() Map<String, String> params)
	{
		System.out.println(memberid);
		System.out.println(params);
		int rows=Integer.parseInt(params.get("rows"));
		int cols=Integer.parseInt(params.get("cols"));
		System.out.println(rows);
		System.out.println(cols);
		
		List<Integer> flatarray=new ArrayList<>();
		for(int i=0;i<params.size();i++)
		{
			if(params.containsKey("orders["+i+"]"))
			{
				flatarray.add(Integer.parseInt(params.get("orders["+i+"]")));
			}
		}
		
		List<List<Integer>> ordersarray=new ArrayList<>();
		int index=0;
		
		
		for(int i=0;i<rows;i++)
		{
			List<Integer> row=new ArrayList<>();
			for(int j=0;j<cols;j++)
			{
			if(index<flatarray.size())
			{
				row.add(flatarray.get(index++));
			}
			else
			{
				row.add(null);
			}
			}
			ordersarray.add(row);
		}
//		System.out.println(ordersarray);
		orderService.ordersDrug(memberid,ordersarray);
		return "paymentdone";
		
	}
	
	@GetMapping("/cartdata/{id}/{val}")
	public String getCartData(@PathVariable("id") int memberid,@PathVariable("val") int value,Model model)
	{
		if(value==1)
		{
		List<Drug> cartdrugs=orderService.getMemberCartDetails(memberid);
			model.addAttribute("alldrugs", cartdrugs);
			model.addAttribute("memberid", memberid);
			return "cartdetails";
		}
		else
		{
			model.addAttribute("cartdrugs", orderService.getMemberCartDetails(memberid));
			model.addAttribute("memberid", memberid);
			return "Cart";
		}
	}
	
	@GetMapping("/orderdata/{memberid}/{val}")
	public String getOrderData(@PathVariable("memberid") int memberid,@PathVariable("val") int value,Model model)
	{

	if(value==1)
	{
	List<Drug> orderdrugs=orderService.getMemberOrdersDetails(memberid);
		model.addAttribute("orderdrugs", orderdrugs);
		model.addAttribute("memberid", memberid);
		return "orderdetails";
	}
	else
	{
		model.addAttribute("orderdrugs", orderService.getMemberOrdersDetails(memberid));
		model.addAttribute("memberid", memberid);
		return "Orders";
	}
	}
//	@PostMapping("/cartdata/{id}/order/{orderedlist}")
//	public void OrderData(@PathVariable("id") int memberid,@PathVariable("orderedlist") List<Integer> orderlist,Model model)
//	{
//		System.out.println(memberid);
//		System.out.println(orderlist);
//	}
	
	
}
	
