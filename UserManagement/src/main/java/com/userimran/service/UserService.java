package com.userimran.service;

import java.util.Map;

import com.userimran.dto.LoginDto;
import com.userimran.dto.RegisterDto;
import com.userimran.dto.ResetPwdDto;
import com.userimran.dto.UserDto;

public interface UserService {

	public Map<Integer, String> getCountries();
	public Map<Integer, String> getStates(Integer cid);
	public Map<Integer, String> getCities(Integer sid);
	public UserDto getUser(String email);
	public boolean regiterUser(RegisterDto regDto);
	public UserDto getUser(LoginDto loginDto);
	public boolean resetPwd(ResetPwdDto pwdDto);
	public String getQuote();  // api call
}
