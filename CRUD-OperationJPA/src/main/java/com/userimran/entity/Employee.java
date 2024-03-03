package com.userimran.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	private int eid;
	private String ename;
	private String edesignation;
	private double esalary;
}
