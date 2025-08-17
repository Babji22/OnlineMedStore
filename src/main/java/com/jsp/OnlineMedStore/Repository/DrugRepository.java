package com.jsp.OnlineMedStore.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.OnlineMedStore.entity.Drug;

public interface DrugRepository extends JpaRepository<Drug, Integer>
{
	public Optional<Drug> findByName(String name);
}
