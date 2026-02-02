package com.jsp.OnlineMedStore.DTO;

import java.time.LocalDate;

import com.jsp.OnlineMedStore.entity.Address;
import lombok.Data;

@Data
public class MemberDTO 
{
		
      private Integer id;
	  private String name;
	  private String gender;
	  private LocalDate date;
	  private String email;
	  private String password;
	  private Long mobilenumber;
	  private AddressDTO addressDTO;
	  private boolean disabled;
}
