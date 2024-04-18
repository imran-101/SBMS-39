package com.userimran.controller;

import java.util.Map;

import org.eclipse.angus.mail.handlers.message_rfc822;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.userimran.dto.LoginDto;
import com.userimran.dto.RegisterDto;
import com.userimran.dto.ResetPwdDto;
import com.userimran.dto.UserDto;
import com.userimran.service.UserService;
import com.userimran.utils.AppConstants;
import com.userimran.utils.AppProperties;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProperties props;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("registerDto", new RegisterDto());
		model.addAttribute("countries", userService.getCountries());
		return"registerView";
	}
	
	@GetMapping("/states/{cid}")
	@ResponseBody
	public Map<Integer, String> getStates(@PathVariable("cid")Integer cid){
		return userService.getStates(cid);
	}
	
	@GetMapping("/cities/{sid}")
	@ResponseBody
	public Map<Integer, String> getCities(@PathVariable("sid")Integer sid){
		return userService.getCities(sid);
	}
	
	@PostMapping("/register")
	public String register(RegisterDto regDto,Model model) {
		Map<String, String> message = props.getMessage();
		UserDto user = userService.getUser(regDto.getEmail());
		if(user!=null) {
			model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.DUP_EMAIL));
			return"registerView";
		}
			boolean regiterUser = userService.regiterUser(regDto);
			if(regiterUser) {
				model.addAttribute(AppConstants.SUCC_MSG,message.get(AppConstants.REG_SUCC));
			}else {
				model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.REG_FAIL));
			}
			
			model.addAttribute("countries", userService.getCountries());
		
			return"registerView";
	}
	
	@GetMapping("/")
	public String loginPage(Model model) {
		model.addAttribute("loginDto",new LoginDto());
		return "index";
	}
	
	@PostMapping("/login")
	public String login(LoginDto loginDto, Model model) {
		Map<String, String> message = props.getMessage();
		UserDto user = userService.getUser(loginDto);
		if(user==null) {
			model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.INVALID_CREDENTIALS));
			model.addAttribute("loginDto",new LoginDto());
			return "index";
		}
		
		String pwdUpdated = user.getPwdUpdated();
		if("yes".equals(pwdUpdated)) {
			// pwd already updated go to dashboard
			return "redirect:dashboard";
		}else {
			// pwd not updated - go to resetPwd page
			ResetPwdDto resetPwdDto=new ResetPwdDto();
			resetPwdDto.setEmail(user.getEmail());
			model.addAttribute("resetPwdDto", resetPwdDto);
			return "resetPwdview";
		}
	}
	
	@PostMapping("/resetPwd")
	public String resetPwd(ResetPwdDto pwdDto,Model model) {
		Map<String, String> message = props.getMessage();
		if(!pwdDto.getNewPwd().equals(pwdDto.getConfirmPwd())) {
			model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.PWD_MATCH_ERR));
			return AppConstants.RESET_PWD_VIEW;
		}
		
		UserDto user = userService.getUser(pwdDto.getEmail());
		if(user.getPwd().equals(pwdDto.getOldPwd())) {
			boolean resetPwd = userService.resetPwd(pwdDto);
			if(resetPwd) {
				return "redirect:dashboard";
			}else {
				model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.PWD_UPDATE_ERR));
				return AppConstants.RESET_PWD_VIEW;
			}
		}else {
			model.addAttribute(AppConstants.ERR_MSG,message.get(AppConstants.OLD_PWD_ERR));
			return AppConstants.RESET_PWD_VIEW;
		}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		String quote = userService.getQuote();
		model.addAttribute("quote",quote);
		return "dashboardView";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "index";
	}
	
	
}
