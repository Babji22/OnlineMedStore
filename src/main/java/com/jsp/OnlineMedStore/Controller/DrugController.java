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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.Service.DrugService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@Controller
@RequestMapping("/drug")
public class DrugController 
{
	@Autowired
	DrugService drugService;
	
	@PostMapping("/addnewdrug/savedrug/")
	public String addDrugById(Drug drug)//, @PathVariable("id") int adminid)
	{
		System.out.println(drug);
		drugService.addDrug(drug);//, adminid);
		
		return "redirect:/drug/alldrugs";
	}
	
	@RequestMapping("/newdrug")
	public String Newdrugpage(Model model)
	{
	
		return "Addmedicines";
	}
	
	@GetMapping("/getdrug/{drugid}")
	public String findMember(@PathVariable("drugid")int drugid, Model model)
	{
		ResponseEntity<SuccessResponce> response=drugService.findById(drugid);
		Drug drugdata=(Drug) response.getBody().getData();
		model.addAttribute("drugdata", drugdata);
//		model.addAttribute("adminid", adminid);
//		System.out.println(drugdata.getId());
		return "Updatemedicineform";
		
	}
	
	@RequestMapping("/editdrug")
	public String editDrugById(Drug drug)
	{
		//System.out.println(drug.getId());
		System.out.println(drug);
		drugService.editDrug(drug);
		return "redirect:/drug/alldrugs";
	}
	
	@GetMapping("/getdrugbyid/{id}")
	public String getDrugById(@PathVariable("id") int id, Model model)
	{
		Drug drugdata=(Drug) drugService.findById(id).getBody().getData();
		model.addAttribute("drugdata", drugdata);
		return "Updatemedicineform";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("search") String value,Model model)
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
//		boolean ban = false;
//		if(value.equalsIgnoreCase("true"))
//		{
//			ban=true;
//		}
//		else if(value.equalsIgnoreCase("false"))
//		{
//			ban=false;
//		}
		
		for (Drug drug : alldrugs) {
			
			if((drug.getName().equalsIgnoreCase(value)) || (drug.getCompany().equalsIgnoreCase(value)) || (drug.getType().equalsIgnoreCase(value)) || (drug.getQuantity()==Integer.parseInt(value)) || (drug.getPrice()==Integer.parseInt(value)) || (drug.getRating()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
			{
				drugdetails.add(drug);
			}
		}
		model.addAttribute("alldrugs", drugdetails);
		return "AdminMedicines";
		}
	}
	
	@GetMapping("/getdrugbyname")
	public ResponseEntity<SuccessResponce> getDrugByName(@RequestParam String name)
	{
		return drugService.findByName(name);
	}
	
//	@DeleteMapping("/deletedrug")
//	public ResponseEntity<SuccessResponce> deleteDrugById(@RequestParam int drugid,@RequestParam int adminid)
//	{
//		return drugService.deleteDrug(drugid,adminid);
//	}
	
	@RequestMapping("/deletedrug/{drugid}")
	public String deletedrug(@PathVariable("drugid") int drugid)
	{
//		System.out.println(drugid);
//		System.out.println(adminid);
		drugService.deleteDrug(drugid);
		return "redirect:/drug/alldrugs";
	}
	
//	@GetMapping("/getdrugs")
//	public ResponseEntity<SuccessResponce> getDrugs()
//	{
//		return drugService.findAllDrugs();
//	}
	
	@RequestMapping("/alldrugs")
	public String alldrugssData(Model model)
	{
		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
		model.addAttribute("alldrugs", alldrugs);
//		model.addAttribute("adminid", adminid);
//		System.out.println(alldrugs);
		return "AdminMedicines";
	}
	
}
