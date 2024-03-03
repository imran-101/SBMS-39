package com.userimran.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.userimran.entity.Employee;
import com.userimran.repository.EmpRepositories;

@Service
public class EmpService {
	EmpRepositories emprepos;

	public EmpService(EmpRepositories emprepos) {
		this.emprepos = emprepos;
	}

	public boolean isRecordPresent(int id) {
		boolean isPresent = emprepos.existsById(id);
		return isPresent;
	}

	public void savedetails(Employee emp) {
		emprepos.save(emp);
	}

	public void displayAllData() {
		Iterable<Employee> iterable = emprepos.findAll();
		System.out.println("=======================================");
		for (Employee employee : iterable) {
			System.out.println(employee);
			System.out.println("=======================================");
		}
	}

	public void displayById(int id) {
		Optional<Employee> byId = emprepos.findById(id);
		if (byId.isPresent()) {
			Employee employee = byId.get();
			System.out.println("=======================================");
			System.out.println(employee);
			System.out.println("=======================================");
		} else {
			System.out.println("=======================================");
			System.out.println("Record is not Present...");
			System.out.println("=======================================");
		}

	}

	public void findAllById(List<Integer> Ids) {
		Iterable<Employee> allById = emprepos.findAllById(Ids);
		System.out.println("=======================================");
		allById.forEach(e -> System.out.println(e));
		System.out.println("=======================================");
	}

	public void deletebyId(int id) {
		emprepos.deleteById(id);
	}

	public void findListByName(String name) {
		List<Employee> list = emprepos.findByEname(name);
		if (list.isEmpty()) {
			System.out.println("=======================================");
			System.out.println("No Record is Available in table for the " + name);
			System.out.println("=======================================");
		} else {
			System.out.println("=======================================");
			list.forEach(e -> System.out.println(e));
			System.out.println("=======================================");
		}
	}

	public void findListByEdesignation(String edesignation) {
		List<Employee> list = emprepos.findByEdesignation(edesignation);
		if (list.isEmpty()) {
			System.out.println("=======================================");
			System.out.println("No Record is Available in table for the " + edesignation);
			System.out.println("=======================================");
		} else {
			System.out.println("=======================================");
			list.forEach(e -> System.out.println(e));
			System.out.println("=======================================");
		}
	}

	public void findListByEsalary(double salary) {
		List<Employee> esalary = emprepos.findByEsalary(salary);
		if (esalary.isEmpty()) {
			System.out.println("=======================================");
			System.out.println("No Record is Available in table for Rs." + salary);
			System.out.println("=======================================");
		} else {
			System.out.println("=======================================");
			esalary.forEach(e -> System.out.println(e));
			System.out.println("=======================================");
		}
	}

	public long countRecord() {
		return emprepos.count();
	}

}
