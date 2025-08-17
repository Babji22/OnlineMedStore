package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.OnlineMedStore.DAO.AdminDAO;
import com.jsp.OnlineMedStore.DAO.DrugDAO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;

@Service
public class DrugService 
{
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	DrugDAO drugDAO;
	
	public ResponseEntity<SuccessResponce> addDrug(Drug drug)//,int adminid)
	{
//		Admin admin=adminDAO.findAdmin(adminid);
//		if (admin!=null)
//		{
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.CREATED.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.addDrug(drug))
					.message("Admin added drug successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.CREATED);
//		}
//		else {
//			throw new NotFoundException("Admin id:"+adminid+"is not found");
//		}
	}
	
	public ResponseEntity<SuccessResponce> editDrug(Drug drug)
	{
//		Drug drug1=drugDAO.findById(drug.getId());
//		if (drug1!=null)
//		{
		System.out.println(drug);
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.CREATED.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.editDrug(drug))
					.message("Admin added drug successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.CREATED);
//		}
//		else {
//			throw new NotFoundException("Admin id:"+adminid+"is not found");
//		}
	}
	
	public ResponseEntity<SuccessResponce> findById(int id)
	{
		Drug drug=drugDAO.findById(id);
		if (drug!=null)
		{
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.FOUND.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.addDrug(drug))
					.message("Admin added drug successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.FOUND);
		}
		else {
			throw new NotFoundException("drug details are not found with drug id:"+id);
		}
	}
	
	public ResponseEntity<SuccessResponce> findByName(String name)
	{
		Drug drug=drugDAO.findByDrugName(name);
		if (drug!=null)
		{
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.FOUND.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.addDrug(drug))
					.message("Admin added drug successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.FOUND);
		}
		else {
			throw new NotFoundException("drug details are not found with drug name:"+name);
		}
	}
	
	public ResponseEntity<SuccessResponce> deleteDrug(int drugid)
	{
//		Admin admin=adminDAO.findAdmin(adminid);
		Drug drug2=drugDAO.deleteDrug(drugid);
//		if (admin!=null)
//		{
			if(drug2!=null)
			{
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.FOUND.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.deleteDrug(drugid))
					.message("Admin deleted drug successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.CREATED);
			}
			else 
			{
				throw new NotFoundException("Drug id:"+drugid+"is not found");
			}
//		}
//		else {
//			throw new NotFoundException("Admin id:"+adminid+"is not found");
//		}
	}
	
	public ResponseEntity<SuccessResponce> findAllDrugs()
	{
			SuccessResponce addData=
					SuccessResponce.builder()
					.status(HttpStatus.FOUND.value())
					.datatime(LocalDateTime.now())
					.data(drugDAO.findAllDrugs())
					.message("drug fetched successfully").build();
			
			return new ResponseEntity<SuccessResponce>(addData,HttpStatus.FOUND);
		}
}
