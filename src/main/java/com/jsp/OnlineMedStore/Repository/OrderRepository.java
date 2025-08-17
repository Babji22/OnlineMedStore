package com.jsp.OnlineMedStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.OnlineMedStore.entity.Ordered;

public interface OrderRepository extends JpaRepository<Ordered, Integer>
{

}
