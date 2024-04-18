package com.userimran.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userimran.entity.UserEntity;
import java.util.List;



public interface UserRepo extends JpaRepository<UserEntity, Integer>{

	public UserEntity findByEmail(String email);
	
	public UserEntity findByEmailAndPwd(String email, String pwd);
}
