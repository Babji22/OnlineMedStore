package com.jsp.OnlineMedStore.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.DTO.MemberDTO;
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

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping("/Registeration")
	public String MembersPage()
	{
		return "Register";
	}
	
	@GetMapping("/Register")
	public String MembershomePage()
	{
		return "path";
	}
	

	
	
	
	@PostMapping("/register")
	public String saveMember(@RequestBody MemberDTO memberDTO)
	{
		memberService.saveMember(memberDTO);
		return "redirect:/Medicine";
	}
	
	@GetMapping("/getmember/{id}")
	public MemberDTO findMember(@PathVariable("id") int id)
	{
		ResponseEntity<MemberDTO> response=memberService.findMember(id);
		MemberDTO memberdata=(MemberDTO) response.getBody();
		return memberdata;
//		model.addAttribute("memberdata", memberdata);
//		model.addAttribute("val", val);
//		return "update";
		
	}
	
	@PutMapping("/update")
	public MemberDTO updateMember(@RequestBody MemberDTO memberDTO)
	{
		return memberService.updateMember(memberDTO).getBody();

	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteMember(@PathVariable("id") int id)
	{
		memberService.deleteMember(id);
		return "redirect:/member/findall";
	}
	

	
	@GetMapping("/findall")
	public List<MemberDTO> findMembers(Model model)
	{
		
		ResponseEntity<List<MemberDTO>> response=memberService.findMembers();

		List<MemberDTO> allmembers=(List<MemberDTO>) response.getBody();
		return allmembers;

	}
	
	@GetMapping("/dealspage/{memberid}")
	public String DealsPage(@PathVariable("memberid") Integer memberid, Model model)
	{
		ResponseEntity<List<DrugDTO>> response=drugService.findAllDrugs();
		List<DrugDTO> alldrugs=(List<DrugDTO>) response.getBody();
		model.addAttribute("alldrugs", alldrugs);
		model.addAttribute("member",memberid);
		return "deals";
	}
	

	
	@GetMapping("/login/{email}/{password}")
	public MemberDTO login(@PathVariable("email") String email,@PathVariable("password") String password,Model model)
	{

		ResponseEntity<MemberDTO> response=memberService.MemberLogin(email, password);
		MemberDTO memberlogin=(MemberDTO) response.getBody();
		System.out.println(memberlogin);
		return memberlogin;
	}
	
	
}
	
