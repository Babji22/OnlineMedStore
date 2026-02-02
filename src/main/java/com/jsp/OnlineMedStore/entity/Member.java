package com.jsp.OnlineMedStore.entity;

import java.time.LocalDate;

import com.jsp.OnlineMedStore.DTO.AddressDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private String gender;
  @Column(unique = true)
  private String email;
  private String password;
  private Long mobilenumber;
  @OneToOne()
  @JoinColumn(name = "addressId")
  private Address address;
  private boolean disabled;
	
}
