package com.jsp.OnlineMedStore.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.jsp.OnlineMedStore.Repository.MemberRepository;
import com.jsp.OnlineMedStore.Util.SuccessResponce;
import com.jsp.OnlineMedStore.entity.Admin;
import com.jsp.OnlineMedStore.entity.Member;

@Component
public class MemberDAO 
{
	@Autowired
	MemberRepository memberRepository;
	
	public Member saveMember(Member member)
	{
		return memberRepository.save(member);
	}

	public Member updateMember(Member member) {
		for (Member member1 : memberRepository.findAll())
		{
			if(member1.getId()==member.getId())
			{
				return memberRepository.save(member);
			}
		}
		return null;
	}

	public Member deleteMember(int id) {
		
		for (Member member1 : memberRepository.findAll())
		{
			if(member1.getId()==id)
			{
				memberRepository.deleteById(id);
				return member1;
			}
		
		}
		return null;
	}

	public Member findMember(int id) {
		
		Optional<Member> member=memberRepository.findById(id);
		if(member.isPresent())
		{
			return member.get();
		}
		else
		{
		return null;
		}
		
	}
	
	
	public Member loginByEmail(String email)
	{
				return memberRepository.findByEmail(email);
	}
	
	public Member loginByPassword(String password)
	{

				return memberRepository.findByPassword(password);
	}
	
	
public List<Member> findMembers() {
		
		return memberRepository.findAll();
		
	}
	
}
