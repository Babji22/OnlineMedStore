package com.jsp.OnlineMedStore.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jsp.OnlineMedStore.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>
{
	Optional<Member> findByEmail(String email);
	Optional<Member> findByPassword(String password);
}
