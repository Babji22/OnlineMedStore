package com.jsp.OnlineMedStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.OnlineMedStore.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>
{
	Admin findByEmail(String email);
	Admin findByPassword(String password);
}
