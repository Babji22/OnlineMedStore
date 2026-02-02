package com.jsp.OnlineMedStore.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Exception.ResourceAlreadyExistException;
import com.jsp.OnlineMedStore.Repository.AdminRepository;
import com.jsp.OnlineMedStore.Repository.DrugRepository;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;

@Service
public class DrugService 
{

	
	@Autowired
	DrugRepository drugRepository;
	
	public ResponseEntity<DrugDTO> addDrug(DrugDTO drugDTO)//,int adminid)
	{

		if(drugRepository.findById(drugDTO.getId()).isPresent())
		{
			throw new ResourceAlreadyExistException("Drug Already Exist");
		}
		Drug drug=fromDTOToEntity(drugDTO);
		Drug drugdata=drugRepository.save(drug);
		if(drugdata.getId()!=null)
		{
			return new ResponseEntity<DrugDTO>(fromEntityToDTO(drugdata), HttpStatus.CREATED);
		}
		else
		{
			throw new RuntimeException("Registration Unsuccessfull");
		}
	}
	
	public ResponseEntity<DrugDTO> editDrug(DrugDTO drugDTO)
	{
		
		List<Drug> allDrugs=drugRepository.findAll();
		for (Drug drug : allDrugs) {
			if(drug.getId()==drugDTO.getId())
			{
				Drug drugData=fromDTOToEntity(drugDTO);
				DrugDTO drugDTOData=fromEntityToDTO(drugRepository.save(drugData));
				return new ResponseEntity<DrugDTO>(drugDTOData, HttpStatus.ACCEPTED);
				
			}
		}
		throw new NotFoundException("Drug is not found");
		

	}
	
	public ResponseEntity<DrugDTO> findById(int id)
	{	
		Optional<Drug> drugOptional=drugRepository.findById(id);
		if(drugOptional.isPresent())
		{
			Drug drug=drugOptional.get();
			return new ResponseEntity<DrugDTO>(fromEntityToDTO(drug),HttpStatus.FOUND);
		}
		else {
			throw new NotFoundException("drug details are not found with drug id:"+id);
		}
	}
	
	public ResponseEntity<DrugDTO> findByName(String name)
	{
		
		Optional<Drug> drug=drugRepository.findByName(name);
		if (drug!=null)
		{
			Drug drugfound=drug.get();
			return new ResponseEntity<DrugDTO>(fromEntityToDTO(drugfound),HttpStatus.FOUND);
		}
		else {
			throw new NotFoundException("drug details are not found with drug name:"+name);
		}
	}
	
	public ResponseEntity<String> deleteDrug(int drugid)
	{
		
		Optional<Drug> drugfound=drugRepository.findById(drugid);
		if(drugfound.isPresent())
		{
			drugRepository.deleteById(drugid);
//			Drug deleteddrug=drugfound.get();
			return new ResponseEntity<String>("Drug Deleted Sucessfully",HttpStatus.NO_CONTENT);
		}
		

			else 
			{
				throw new NotFoundException("Drug id: "+drugid+" is not found");
			}

	}
	
	public ResponseEntity<List<DrugDTO>> findAllDrugs()
	{
			
		 List<Drug> drugList= drugRepository.findAll();
		 List<DrugDTO> drugDTOList=new ArrayList<DrugDTO>();
		 for(Drug drug: drugList)
		 {
			 drugDTOList.add(fromEntityToDTO(drug));
		 }
			return new ResponseEntity<List<DrugDTO>>(drugDTOList,HttpStatus.FOUND);
		}
	
	
	
	
	
	private DrugDTO fromEntityToDTO(Drug drug) {
	    DrugDTO drugDTO = new DrugDTO();
	    
	    drugDTO.setId(drug.getId());
	    drugDTO.setName(drug.getName());
	    drugDTO.setCompany(drug.getCompany());
	    drugDTO.setType(drug.getType());
	    drugDTO.setPrice(drug.getPrice());
	    drugDTO.setQuantity(drug.getQuantity());
	    drugDTO.setRating(drug.getRating());
//	    drugDTO.setBanned(drug.getBanned()); 
	    
	    return drugDTO;
	}

	private Drug fromDTOToEntity(DrugDTO drugDTO) {
	    Drug drug = new Drug();
	    
	    drug.setId(drugDTO.getId());
	    drug.setName(drugDTO.getName());
	    drug.setCompany(drugDTO.getCompany());
	    drug.setType(drugDTO.getType());
	    drug.setPrice(drugDTO.getPrice());
	    drug.setQuantity(drugDTO.getQuantity());
	    drug.setRating(drugDTO.getRating());
//	    drug.setBanned(drugDTO.isBanned());
	    
	    return drug;
	}
}
