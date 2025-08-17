package com.jsp.OnlineMedStore.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.OnlineMedStore.Repository.DrugRepository;
import com.jsp.OnlineMedStore.entity.Drug;

@Component
public class DrugDAO 
{
	@Autowired
	DrugRepository drugRepository;
	
	public Drug addDrug(Drug drug)
	{
		return drugRepository.save(drug);
	}
	
	public Drug editDrug(Drug drug)
	{
		Optional<Drug> optionaldrug=drugRepository.findById(drug.getId());
		if(optionaldrug.isPresent())
		{
			Drug dbDrug=optionaldrug.get();
			//System.out.println(dbDrug);
			if (dbDrug.getName()!=null) {
				dbDrug.setName(drug.getName());
			}
			
			if(dbDrug.getCompany()!=null)
			{
				dbDrug.setCompany(drug.getCompany());
			}
			
			if(dbDrug.getPrice()>=0)
			{
				dbDrug.setPrice(drug.getPrice());
			}
			
			if(dbDrug.getQuantity()!=0)
			{
				dbDrug.setQuantity(drug.getQuantity());
			}
			
			if(dbDrug.getRating()!=0)
			{
				dbDrug.setRating(drug.getRating());
			}
			
			if(dbDrug.getType()!=null)
			{
				dbDrug.setType(drug.getType());
			}
			return drugRepository.save(dbDrug);
			
			
		}
		return null;
	}

	public Drug findById(int id)
	{
		Optional<Drug> drugOptional=drugRepository.findById(id);
		if(drugOptional.isPresent())
		{
			Drug drug=drugOptional.get();
			return drug;
		}
		return null;
	}
	
	public Drug findByDrugName(String name)
	{
		Optional<Drug> drug=drugRepository.findByName(name);
		if (drug!=null)
		{
			Drug drugfound=drug.get();
			return drugfound;
		}
		else
		{
			return null;
		}
	}
	
	public Drug deleteDrug(int id)
	{
		Optional<Drug> drugfound=drugRepository.findById(id);
		if(drugfound.isPresent())
		{
			drugRepository.deleteById(id);
			Drug deleteddrug=drugfound.get();
			return deleteddrug;
		}
		return null;
	}
	
	public List<Drug> findAllDrugs()
	{
		return drugRepository.findAll();
	}
}
