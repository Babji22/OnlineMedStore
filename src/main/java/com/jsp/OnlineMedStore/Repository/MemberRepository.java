package com.jsp.OnlineMedStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jsp.OnlineMedStore.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
	Member findByEmail(String email);
	Member findByPassword(String password);
}
