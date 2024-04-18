package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPwdDto {

	private Integer userId;
	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
}
