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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.Service.DrugService;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@RestController
@RequestMapping("/drug")
@CrossOrigin(origins = "http://localhost:4200")
public class DrugController 
{
	@Autowired
	DrugService drugService;
	
	@PostMapping("/addProduct")
	public String addDrug(@RequestBody DrugDTO drugDTO)
	{
		drugService.addDrug(drugDTO);
		return "redirect:/drug/alldrugs";
	}
	

	@GetMapping("/getdrug/{drugid}")
	public String findMember(@PathVariable("drugid")int drugid, Model model)
	{
		ResponseEntity<DrugDTO> response=drugService.findById(drugid);
		DrugDTO drugdata=(DrugDTO) response.getBody();
		model.addAttribute("drugdata", drugdata);
		return "Updatemedicineform";
		
	}
	
	@PutMapping("/update")
	public String editDrugById(@RequestBody DrugDTO drugDTO)
	{
		drugService.editDrug(drugDTO);
		return "redirect:/drug/alldrugs";
	}
	
	@GetMapping("/{id}")
	public DrugDTO getDrugById(@PathVariable("id") int id, Model model)
	{
		DrugDTO drugdata=(DrugDTO) drugService.findById(id).getBody();
		return drugdata;

	}
	
//	@GetMapping("/search")
//	public String search(@RequestParam("search") String value,Model model)
//	{
//		System.out.println(value);
//		if(value=="")
//		{
//			return "redirect:/drug/alldrugs"; 
//		}
//		else
//		{
//		ResponseEntity<List<DrugDTO>> response=drugService.findAllDrugs();
//		List<DrugDTO> alldrugs=(List<DrugDTO>) response.getBody();
//		ArrayList<DrugDTO> drugdetails=new ArrayList<DrugDTO>();
//		
//		for (DrugDTO drugDTO : alldrugs) {
//			
//			if((drugDTO.getName().equalsIgnoreCase(value)) || (drugDTO.getCompany().equalsIgnoreCase(value)) || (drugDTO.getType().equalsIgnoreCase(value)) || (drugDTO.getQuantity()==Integer.parseInt(value)) || (drugDTO.getPrice()==Integer.parseInt(value)) || (drugDTO.getRating()==Integer.parseInt(value)))// || (drug.isBanned()==ban))
//			{
//				drugdetails.add(drugDTO);
//			}
//		}
//		model.addAttribute("alldrugs", drugdetails);
//		return "AdminMedicines";
//		}
//	}
	
	@GetMapping("/getdrugbyname")
	public ResponseEntity<DrugDTO> getDrugByName(@RequestParam String name)
	{
		return drugService.findByName(name);
	}
	
	
	@DeleteMapping("/{drugid}")
	public String deletedrug(@PathVariable("drugid") int drugid)
	{
		drugService.deleteDrug(drugid);
		return "redirect:/drug/alldrugs";
	}
	
	
	@GetMapping("/alldrugs")
	public List<DrugDTO> alldrugssData(Model model)
	{
		ResponseEntity<List<DrugDTO>> response=drugService.findAllDrugs();
		List<DrugDTO> alldrugs=(List<DrugDTO>) response.getBody();
		return alldrugs;
	}
	
}
