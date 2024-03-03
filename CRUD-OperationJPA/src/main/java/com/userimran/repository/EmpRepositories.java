package com.userimran.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userimran.entity.Employee;

@Repository
public interface EmpRepositories extends CrudRepository<Employee, Integer> {
	
	public List<Employee> findByEname(String ename);
	public List<Employee> findByEdesignation(String edesignation);
	public List<Employee> findByEsalary(double esalary);

}
