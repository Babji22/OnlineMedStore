package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jsp.OnlineMedStore.DAO.AdminDAO;
import com.jsp.OnlineMedStore.DAO.DrugDAO;
import com.jsp.OnlineMedStore.DAO.MemberDAO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;

@Service
public class AdminService 
{
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	DrugDAO drugDAO;
	
	public ResponseEntity<SuccessResponce> saveAdmin(Admin admin)
	{
		SuccessResponce data= SuccessResponce.builder().status(HttpStatus.CREATED.value()).datatime(LocalDateTime.now()).data(adminDAO.saveAdmin(admin)).message("Admin saved Successfully").build();
		return new ResponseEntity<SuccessResponce>(data, HttpStatus.CREATED);
	}
	
	public ResponseEntity<SuccessResponce> updateAdmin(Admin admin)
	{
		SuccessResponce update=SuccessResponce.builder().status(HttpStatus.ACCEPTED.value()).datatime(LocalDateTime.now()).data(adminDAO.updateAdmin(admin)).message("Admin details Updated").build();
		return new ResponseEntity<SuccessResponce>(update, HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<SuccessResponce> findAdmin(int id)
	{
		if(adminDAO.findAdmin(id)!=null)
		{
		SuccessResponce update=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).data(adminDAO.findAdmin(id)).message("Admin details Updated").build();
		return new ResponseEntity<SuccessResponce>(update, HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Admin "+id+" is not found");
		}
	}
	
	public ResponseEntity<SuccessResponce> deleteAdmin(int id)
	{
		if(adminDAO.deleteAdmin(id)!=null)
		{
		SuccessResponce delete=SuccessResponce.builder().status(HttpStatus.OK.value()).datatime(LocalDateTime.now()).data(adminDAO.deleteAdmin(id)).message("Admin details Updated").build();
		return new ResponseEntity<SuccessResponce>(delete, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Admin "+id+" is not found");
		}
	}
	
	public ResponseEntity<SuccessResponce> AdminLogin(String email,String password)
	{
		if (adminDAO.loginByEmail(email)!=null)
		{
			if(adminDAO.loginByPassword(password)!=null)
			{
				if(adminDAO.loginByPassword(password).getEmail().equals(email))
				{
					SuccessResponce login=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).data(adminDAO.loginByEmail(email)).message("Admin details found").build();
					return new ResponseEntity<SuccessResponce>(login, HttpStatus.FOUND);
				}
				else
				{
					throw new NotFoundException("Admin "+password+" is not found");
				}
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

	
	public List<Integer> AdmindashboardPageService()
	{
		ArrayList<Integer> DashboardData=new ArrayList<Integer>();
		List<Drug> alldrugs=drugDAO.findAllDrugs();
		
		List<Member> allmembers=memberDAO.findMembers();
		DashboardData.add(alldrugs.size());
		DashboardData.add(allmembers.size());
		
		
//		model.addAttribute("allmembers", allmembers.size());
//		ResponseEntity<SuccessResponce> response=drugService.findAllDrugs();
//		List<Drug> alldrugs=(List<Drug>) response.getBody().getData();
//		model.addAttribute("alldrugs", alldrugs.size());
		return DashboardData;
	}
	public ResponseEntity<SuccessResponce> enableMember(int adminid, int memberid) 
	{
		if(adminDAO.findAdmin(adminid)!=null)
		{
			Member member=memberDAO.findMember(memberid);
			if (member!=null) 
			{
				member.setDisabled(true);
				memberDAO.updateMember(member);
				SuccessResponce login=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).data(memberDAO.updateMember(member)).message("Admin details found").build();
				return new ResponseEntity<SuccessResponce>(login, HttpStatus.FOUND);
			}
			else {
				throw new NotFoundException("Member "+memberid+" is not found");
			}
		}
		else {
			throw new NotFoundException("Admin "+adminid+" is not found");
		}
		
	}
	
	public ResponseEntity<SuccessResponce> findMembers() 
	{
		SuccessResponce findall=SuccessResponce.builder().status(HttpStatus.FOUND.value()).datatime(LocalDateTime.now()).message("Members details saved").data(adminDAO.findMembers()).build();
		return new ResponseEntity<SuccessResponce>(findall, HttpStatus.FOUND);
	}
	
	
}
