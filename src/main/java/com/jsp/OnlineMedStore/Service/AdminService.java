package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.OnlineMedStore.DTO.AdminDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Exception.ResourceAlreadyExistException;
import com.jsp.OnlineMedStore.Repository.AdminRepository;
import com.jsp.OnlineMedStore.Repository.DrugRepository;
import com.jsp.OnlineMedStore.Repository.MemberRepository;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@Service
public class AdminService 
{
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DrugRepository drugRepository;
	
	public ResponseEntity<String> saveAdmin(AdminDTO adminDTO)
	{
		if(adminRepository.findByEmail(adminDTO.getEmail()).isPresent())
		{
			throw new ResourceAlreadyExistException("User Already Exist");
		}
		Admin admin=fromDTOToEntity(adminDTO);
		Admin admindata=adminRepository.save(admin);
		
		if(admindata.getId()!=null)
		{
			return new ResponseEntity<String>("registration sucessfull", HttpStatus.CREATED);
		}
		else
		{
			throw new RuntimeException("Registration Unsuccessfull");
		}
		
		
	}
	
	public ResponseEntity<AdminDTO> updateAdmin(AdminDTO adminDTO)
	{
		List<Admin> alladmin=adminRepository.findAll();
		for (Admin admin : alladmin) {
			if(admin.getId()==adminDTO.getId())
			{
				Admin adminData=fromDTOToEntity(adminDTO);
				AdminDTO adminDTOData=fromEntityToDTO(adminRepository.save(adminData));
				return new ResponseEntity<AdminDTO>(adminDTOData, HttpStatus.ACCEPTED);
				
			}
		}
		throw new NotFoundException("Admin is not found");
		
	}
	
	public ResponseEntity<AdminDTO> findAdminByEmail(String email)
	{
		Optional<Admin> admin=adminRepository.findByEmail(email);
		if(admin.isPresent())
		{
		return new ResponseEntity<AdminDTO>(fromEntityToDTO(admin.get()), HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Admin is not found");
		}
	}
	
	public ResponseEntity<String> deleteAdmin(int id)
	{
		for (Admin admin : adminRepository.findAll())
		{
			if(admin.getId()==id)
			{
				adminRepository.deleteById(id);
				return new ResponseEntity<String>("Admin data is sucessfully Deleted", HttpStatus.OK);
			}
		}
		throw new NotFoundException("Admin is not found");
		
	}
	
	public ResponseEntity<AdminDTO> AdminLogin(String email,String password)
	{
		Optional<Admin> getAdminByEmail=adminRepository.findByEmail(email);
		if (getAdminByEmail.isPresent())
		{
			if(getAdminByEmail.get().getPassword().equals(password))
			{
				return new ResponseEntity<AdminDTO>(fromEntityToDTO(getAdminByEmail.get()), HttpStatus.FOUND);
			}
			else
			{
				throw new NotFoundException("Admin "+password+" is not found");
			}
		}
		else
		{
			throw new NotFoundException("Admin "+email+" is not found");
		}
	} 

	

	
	public ResponseEntity<String> enableMember(int adminid, int memberid) 
	{
		if(adminRepository.findById(adminid)!=null)
		{
			Member member=memberRepository.findById(memberid).get();
			if (member!=null) 
			{
				member.setDisabled(true);
				memberRepository.save(member);
				return new ResponseEntity<String>("Enabled Successfully", HttpStatus.FOUND);
			}
			else {
				throw new NotFoundException("Member "+memberid+" is not found");
			}
		}
		else {
			throw new NotFoundException("Admin "+adminid+" is not found");
		}
		
	}
	


      
      private AdminDTO fromEntityToDTO(Admin admin)
  		{
    	AdminDTO adminDTO=new AdminDTO();
    	adminDTO.setId(admin.getId());
    	adminDTO.setEmail(admin.getEmail());
    	adminDTO.setMobilenumber(admin.getMobilenumber());
    	adminDTO.setPassword(admin.getPassword());
    	adminDTO.setName(admin.getName());
    	adminDTO.setGender(admin.getGender());
  		
  		return adminDTO;
  		}
  	
  	private Admin fromDTOToEntity(AdminDTO adminDTO)
  	{
  		Admin admin=new Admin();
  		admin.setEmail(adminDTO.getEmail());
  		admin.setMobilenumber(adminDTO.getMobilenumber());
  		admin.setPassword(adminDTO.getPassword());
  		admin.setName(adminDTO.getName());
  		admin.setId(adminDTO.getId());
  		admin.setGender(adminDTO.getGender());
  		return admin;
  	}
	
}
