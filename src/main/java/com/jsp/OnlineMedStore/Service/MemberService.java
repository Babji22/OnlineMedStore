package com.jsp.OnlineMedStore.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.OnlineMedStore.DTO.AddressDTO;
import com.jsp.OnlineMedStore.DTO.AdminDTO;
import com.jsp.OnlineMedStore.DTO.DrugDTO;
import com.jsp.OnlineMedStore.DTO.MemberDTO;
import com.jsp.OnlineMedStore.DTO.OrderDTO;
import com.jsp.OnlineMedStore.Exception.NotFoundException;
import com.jsp.OnlineMedStore.Exception.ResourceAlreadyExistException;
import com.jsp.OnlineMedStore.Repository.AddressRepository;
import com.jsp.OnlineMedStore.Repository.MemberRepository;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Address;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Drug;
import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

@Service
public class MemberService 
{
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	DrugService drugService;
	
	@Autowired
	AddressService addressService;
	
	public ResponseEntity<MemberDTO> saveMember(MemberDTO memberDTO)
	{
		if(memberRepository.findByEmail(memberDTO.getEmail()).isPresent())
		{
			throw new ResourceAlreadyExistException("User Already Exist");
		}
		Member member=fromDTOToEntity(memberDTO);
		Member memberdata=memberRepository.save(member);
		
		if(memberdata.getId()!=null)
		{
			return new ResponseEntity<MemberDTO>(fromEntityToDTO(memberdata), HttpStatus.CREATED);
		}
		else
		{
			throw new RuntimeException("Registration Unsuccessfull");
		}
		
	}

	public ResponseEntity<MemberDTO> updateMember(MemberDTO memberDTO) {
		
		AddressDTO addressDTO=memberDTO.getAddressDTO();
		Address address= addressService.saveAddress(addressDTO);
		System.out.println(address);
		List<Member> allmember=memberRepository.findAll();
		for (Member member : allmember) {
			if(member.getId()==memberDTO.getId())
			{
				Member memberData=fromDTOToEntity(memberDTO);
				memberData.setAddress(address);
				MemberDTO memberDTOData=fromEntityToDTO(memberRepository.save(memberData));
				memberDTOData.setAddressDTO(addressService.findAddress(address.getId()));
				
				return new ResponseEntity<MemberDTO>(memberDTOData, HttpStatus.ACCEPTED);
				
			}
		}
		throw new NotFoundException("Member is not found");
		
	}

	public ResponseEntity<String> deleteMember(int id) {
		
		for (Member member : memberRepository.findAll())
		{
			if(member.getId()==id)
			{
				memberRepository.deleteById(id);
				return new ResponseEntity<String>("Members details deleted", HttpStatus.OK);
			}
		
		}
		throw new NotFoundException("Member is not found");
		
		
	}

	public ResponseEntity<MemberDTO> findMember(int id) 
	{
		Optional<Member> member=memberRepository.findById(id);
		if(member.isPresent())
		{
			MemberDTO memberDTO=fromEntityToDTO(member.get());
			memberDTO.setAddressDTO(addressService.findAddress(memberDTO.getAddressDTO().getId()));
			return new ResponseEntity<MemberDTO>(memberDTO, HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Member is not found");
		}
		
	}
	
	


	
	

	
	public ResponseEntity<MemberDTO> MemberLogin(String email,String password)
	{
		Optional<Member> getMemberByEmail=memberRepository.findByEmail(email);
		System.out.println(getMemberByEmail.get().getAddress());
		if (getMemberByEmail.isPresent())
		{
			if(getMemberByEmail.get().getPassword().equals(password))
			{
				MemberDTO memberDTO=fromEntityToDTO(getMemberByEmail.get());
				memberDTO.setAddressDTO(addressService.findAddress(getMemberByEmail.get().getAddress().getId()));
				return new ResponseEntity<MemberDTO>(memberDTO, HttpStatus.FOUND);
			}
			else
			{
				throw new NotFoundException("Member "+password+" is not found");
			}
		}
		else
		{
			throw new NotFoundException("Member "+email+" is not found");
		}
	}
	
	

	
	
	
	public ResponseEntity<List<MemberDTO>> findMembers() {
		
		List<Member> memberList= memberRepository.findAll();
		
		List<MemberDTO> newMemberList=new ArrayList<MemberDTO>();
		for(Member member:memberList)
		{
			newMemberList.add(fromEntityToDTO(member));
		}
		
		return new ResponseEntity<List<MemberDTO>>(newMemberList, HttpStatus.FOUND);
		
	}

	private MemberDTO fromEntityToDTO(Member member)
	{
		MemberDTO memberDTO=new MemberDTO();
		memberDTO.setId(member.getId());
		memberDTO.setEmail(member.getEmail());
		memberDTO.setMobilenumber(member.getMobilenumber());
		memberDTO.setPassword(member.getPassword());
		memberDTO.setName(member.getName());
		memberDTO.setGender(member.getGender());
		memberDTO.setDisabled(member.isDisabled());
		
		return memberDTO;
	}

	private Member fromDTOToEntity(MemberDTO memberDTO)
	{
		Member member=new Member();
		member.setEmail(memberDTO.getEmail());
		member.setMobilenumber(memberDTO.getMobilenumber());
		member.setPassword(memberDTO.getPassword());
		member.setName(memberDTO.getName());
		member.setId(memberDTO.getId());
		member.setGender(memberDTO.getGender());
		member.setDisabled(memberDTO.isDisabled());
		return member;
	}
	
}
