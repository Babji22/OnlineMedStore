package com.jsp.OnlineMedStore.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.OnlineMedStore.Repository.AdminRepository;
import com.jsp.OnlineMedStore.Repository.MemberRepository;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Member;

@Component
public class AdminDAO 
{
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	public Admin saveAdmin(Admin admin)
	{
		return adminRepository.save(admin);
	}
	
	public Admin updateAdmin(Admin admin)
	{
		List<Admin> alladmin=adminRepository.findAll();
		for (Admin admin2 : alladmin) {
			if(admin2.getId()==admin.getId())
			{
				return adminRepository.save(admin);
			}
		}
		return null;
	}
	
	public Admin findAdmin(int id)
	{
		
		Optional<Admin> admin=adminRepository.findById(id);
		if(admin.isPresent())
		{
			return admin.get();
		}
		else
		{
		return null;
		}
	}
	
	public Admin deleteAdmin(int id)
	{
		for (Admin admin : adminRepository.findAll())
		{
			if(admin.getId()==id)
			{
				adminRepository.deleteById(id);
				return admin;
			}
		}
		return null;
	}
	
	
		
	public Admin loginByEmail(String email)
	{
//		for(Admin admin: adminRepository.findAll())
//		{
//			if(admin.getEmail().equalsIgnoreCase(email))
//			{
				return adminRepository.findByEmail(email);
//			}
//		}
//		return null;
	}
	
	public Admin loginByPassword(String password)
	{
//		for(Admin admin: adminRepository.findAll())
//		{
//			if(admin.getPassword().equalsIgnoreCase(password))
//			{
				return adminRepository.findByPassword(password);
//			}
//		}
//		return null;
	}
	
//	public Admin login(String email,String password)
//	{
//		for(Admin admin: adminRepository.findAll())
//		{
//			if(admin.getEmail().equalsIgnoreCase(email))
//			{
//				if(admin.getPassword().equalsIgnoreCase(password))
//				{
//					return adminRepository.findByEmail(password);
//				}
//				else
//				{
//					return null;
//				}
//			}
//			
//		return null;
//		}
//		return null;
//	}
	
      public List<Member> findMembers() {
		
		return memberRepository.findAll();
		
	}
}

