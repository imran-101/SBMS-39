package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

	private String name;
	private String email; 
	private String phno; 
	private String pwd;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
}
