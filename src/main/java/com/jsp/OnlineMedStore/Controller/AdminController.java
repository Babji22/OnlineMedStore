package com.jsp.OnlineMedStore.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.Service.AdminService;
import com.jsp.OnlineMedStore.Service.DrugService;
import com.jsp.OnlineMedStore.Service.MemberService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@Controller
//@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	DrugService drugService;
	
	
	@RequestMapping("/Medicine")
	public String MedicinePage()
	{
		
		return "home";
	}
	
	
	@RequestMapping("/dealspage")
	public String DealsPage(Model model)
	{
		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
		Collections.sort(alldrugs,new check());
		System.out.println(alldrugs);
		model.addAttribute("alldrugs", alldrugs);
		return "deals";
	}
	
	
	
	
	
	@RequestMapping("/dashboard")
	public String AdmindashboardPage(Model model)
	{
		List<Integer> dashboardData=adminService.AdmindashboardPageService();
		model.addAttribute("alldrugs", dashboardData.get(0));
		model.addAttribute("allmembers", dashboardData.get(1));
		return "AdminDashboard";
	}
	
	@RequestMapping("/path")
	public String Registrationpage(@RequestParam("val") String val)
	{
		if(val.equals("Admin"))
		{
		return "path";
		}
		else
		{
			return "redirect:member/Register";
		}
	}
	
	@RequestMapping("/register")
	public String AdminRegister(Admin admin)
	{
//		   	System.out.println(val);
//		   	if(val.equals("Admin"))
//			{
//			adminService.saveAdmin((Admin) admin);
//			}
//		   	else
//		   	{
//		   		memberService.saveMember((Member) admin);
//		   	}
//			return "home";
		   	
//		   	if(val.equals("Admin"))
//		   	{
		   		adminService.saveAdmin(admin);
		   		return "home";
//		   	}
//		   	else
//		   	{
//		   		Member member=(Member)admin;
//		   		System.out.println(member);
//		   		return "redirect:/member/register/"+member;
//		   	}
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponce> AdminUpdate(@RequestBody Admin admin)
	{
		return adminService.updateAdmin(admin);
	}
	
	@GetMapping("/find")
	public ResponseEntity<SuccessResponce> findAdmin(@RequestParam int id)
	{
		return adminService.findAdmin(id);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponce> deleteAdmin(@RequestParam int id)
	{
		return adminService.deleteAdmin(id);
	}
	
	@GetMapping("/search")
	public String casualsearch(@RequestParam("search") String value,Model model)
	{
		System.out.println(value);
		if(value=="")
		{
			return "redirect:/drug/alldrugs"; 
		}
		else
		{
		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
		ArrayList<Drug> drugdetails=new ArrayList<Drug>();
		
		for (Drug drug : alldrugs) {
			
			if((drug.getName().equalsIgnoreCase(value)) || (drug.getCompany().equalsIgnoreCase(value)) || (drug.getType().equalsIgnoreCase(value)))// || (drug.getQuantity()==Integer.parseInt(value)) || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
			{
				drugdetails.add(drug);
			}
		}
		model.addAttribute("alldrugs", drugdetails);
		return "AdminMedicines";
		}
	}
	
	@GetMapping("/login")
	public String loginAdmin(@RequestParam("val") String val,String email,String password,Model model)
	{
		System.out.println(val);
		if(val.equals("Admin"))
		{
		ResponseEntity<SuccessResponce> response=adminService.AdminLogin(email, password);
		Admin responseadmin=(Admin) response.getBody().getData();
		model.addAttribute("responseadmin", responseadmin);
		return "Admin";	
		}
		else
		{
			ResponseEntity<SuccessResponce> response=memberService.MemberLogin(email, password);
			Member memberlogin=(Member) response.getBody().getData();
			model.addAttribute("memberlogin", memberlogin);
			return "home2";
		}
	}
	
	@GetMapping("/enablemember")
	public ResponseEntity<SuccessResponce> enableMember(@RequestParam int adminid,@RequestParam int memberid)
	{
		return adminService.enableMember(adminid,memberid);
	}
	
	@RequestMapping("/logout")
	public String logout()
	{
		return "redirect:/Medicine";
	}
	
	
	@GetMapping("/findall")
	public List<Member> findMembers()
	{
		ResponseEntity<SuccessResponce> response=adminService.findMembers();
		List<Member> allmembers=(List<Member>) response.getBody().getData();
		return allmembers;
	}


	
}

class check implements Comparator<Drug>
{
	@Override
	public int compare(Drug o1, Drug o2) 
	{
		return o1.getType().compareTo(o2.getType());
		
	}
}