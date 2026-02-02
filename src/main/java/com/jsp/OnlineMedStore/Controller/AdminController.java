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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.DTO.AdminDTO;
import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.DTO.MemberDTO;
import com.jsp.OnlineMedStore.Service.AdminService;
import com.jsp.OnlineMedStore.Service.DrugService;
import com.jsp.OnlineMedStore.Service.MemberService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController
{
	@Autowired
	AdminService adminService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	DrugService drugService;
	
	
	
	
	@PostMapping("/register")
	public String AdminRegister(@RequestBody AdminDTO adminDTO)
	{
		adminService.saveAdmin(adminDTO);
		return "Registration sucessfull";
	}
	
	@PutMapping("/update")
	public AdminDTO AdminUpdate(@RequestBody AdminDTO adminDTO)
	{
		return adminService.updateAdmin(adminDTO).getBody();
	}
	
	@GetMapping("/find")
	public ResponseEntity<AdminDTO> findAdminByEmail(@RequestParam String email)
	{
		return adminService.findAdminByEmail(email);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAdmin(@RequestParam int id)
	{
		return adminService.deleteAdmin(id);
	}
	
	@GetMapping("/search")
	public String casualsearch(@RequestParam("search") String value,Model model)
	{
		if(value=="")
		{
			return "redirect:/drug/alldrugs"; 
		}
		else
		{
		ResponseEntity<List<DrugDTO>> response=drugService.findAllDrugs();
		List<DrugDTO> alldrugs=(List<DrugDTO>) response.getBody();
		ArrayList<DrugDTO> drugdetails=new ArrayList<DrugDTO>();
		
		for (DrugDTO drugDTO : alldrugs) {
			
			if((drugDTO.getName().equalsIgnoreCase(value)) || (drugDTO.getCompany().equalsIgnoreCase(value)) || (drugDTO.getType().equalsIgnoreCase(value)))// || (drug.getQuantity()==Integer.parseInt(value)) || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
			{
				drugdetails.add(drugDTO);
			} 
		}
		model.addAttribute("alldrugs", drugdetails);
		return "AdminMedicines";
		}
	}
	
	@GetMapping("/login/{email}/{password}")
	public AdminDTO loginAdmin(@PathVariable String email,@PathVariable String password)
	{
		return adminService.AdminLogin(email, password).getBody();
	}
	
	@GetMapping("/enablemember")
	public ResponseEntity<String> enableMember(@RequestParam int adminid,@RequestParam int memberid)
	{
		return adminService.enableMember(adminid,memberid);
	}
	
	

	
}
