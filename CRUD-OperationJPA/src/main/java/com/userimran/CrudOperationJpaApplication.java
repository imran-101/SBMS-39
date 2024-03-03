package com.userimran;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.userimran.entity.Employee;
import com.userimran.service.EmpService;

@SpringBootApplication
public class CrudOperationJpaApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(CrudOperationJpaApplication.class, args);

		EmpService empService = context.getBean(EmpService.class);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			System.out.println("PRESS :->");
			System.out.println("1: Insert One Record");
			System.out.println("2: Update One Record By ID");
			System.out.println("3: Display By ID");
			System.out.println("4: Display All By ID");
			System.out.println("5: Display All Record");
			System.out.println("6: DisplayByName");
			System.out.println("7: DisplayByDesignation");
			System.out.println("8: DisplayBySalary");
			System.out.println("9: DeleteById");
			System.out.println("10: Count Record Available in Table");
			System.out.println("11: exit");

			int c = Integer.parseInt(br.readLine());

			if (c == 1) {
				// Insert One Record
				System.out.println("enter data for insert :");
				System.out.println("(id,name,designation,salary)");

				// take the input in the form of string
				String line = br.readLine();

				// split the string data and store in array
				String[] details = line.split(",");

				// parsing the string data into int
				int id = Integer.parseInt(details[0]);

				// to check if record is already present in table with the help of primary key.
				if (empService.isRecordPresent(id)) {
					System.out.println("=======================================");
					System.out.println("Record is already Present");
					System.out.println("=======================================");
				} else {
					Employee emp = new Employee();
					emp.setEid(Integer.parseInt(details[0]));
					emp.setEname(details[1]);
					emp.setEdesignation(details[2]);
					emp.setEsalary(Double.parseDouble(details[3]));

					empService.savedetails(emp);
					System.out.println("=======================================");
					System.out.println("Record Saved");
					System.out.println("=======================================");
				}

			} else if (c == 2) {
				// Update One Record By ID
				System.out.println("enter details for update :");
				System.out.println("(id,name,designation,salary)");

				// take the input in the form of string
				String line = br.readLine();

				// split the string data and store in array
				String[] details = line.split(",");

				// parsing the string data into int
				int id = Integer.parseInt(details[0]);

				// to check if record is already present in table with the help of primary key.
				if (empService.isRecordPresent(id)) {
					Employee emp = new Employee();
					emp.setEid(Integer.parseInt(details[0]));
					emp.setEname(details[1]);
					emp.setEdesignation(details[2]);
					emp.setEsalary(Double.parseDouble(details[3]));

					empService.savedetails(emp);
					System.out.println("=======================================");
					System.out.println("Record is Updated");
					System.out.println("=======================================");
				} else {
					System.out.println("=======================================");
					System.out.println("No Record is Present with id : "+id);
					System.out.println("=======================================");
				}

			} else if (c == 3) {
				// Display By ID
				System.out.println("enter id:");
				int id = Integer.parseInt(br.readLine());
				empService.displayById(id);
			} else if (c == 4) {
				// Display All By ID
				System.out.println("enter id like: id1,id2,id3....");
				String line = br.readLine();

				// Splitting String into String[] based on comma(',').
				String[] str = line.split(",");

				// creating Integer[] size equal of String[]
				Integer[] ids = new Integer[str.length];

				// Converting String[] data into Integer[] data
				for (int i = 0; i < str.length; i++) {
					ids[i] = Integer.parseInt(str[i]);
				}
				List<Integer> list = Arrays.asList(ids);
				empService.findAllById(list);
			}

			else if (c == 5) {
				// Display All Record
				empService.displayAllData();
			}

			else if (c == 6) {
				// Display Record Based on Name
				System.out.println("enter name:");
				String name = br.readLine();
				empService.findListByName(name);
			}

			else if (c == 7) {
				// Display Record Based on Designation
				System.out.println("enter designation:");
				String designation = br.readLine();
				empService.findListByEdesignation(designation);
			}

			else if (c == 8) {
				// Display Record Based on Salary
				System.out.println("enter salary:");
				double salary = Double.parseDouble(br.readLine());
				empService.findListByEsalary(salary);
			}

			else if (c == 9) {
				// Delete Record Based on ID
				System.out.println("enter id:");
				int id = Integer.parseInt(br.readLine());
				if (empService.isRecordPresent(id)) {
					empService.deletebyId(id);
					System.out.println("=======================================");
					System.out.println("id " + id + " data is deleted...");
					System.out.println("=======================================");
				} else {
					System.out.println("=======================================");
					System.out.println("No Record is Present with id : " + id);
					System.out.println("=======================================");
				}

			}

			else if (c == 10) {
				// Retrieve Record Count Available in DB Table.
				long countRecord = empService.countRecord();
				if (countRecord > 0) {
					System.out.println("=======================================");
					System.out.println(countRecord + " Record is Available...");
					System.out.println("=======================================");
				} else {
					System.out.println("=======================================");
					System.out.println("No Record Available...");
					System.out.println("=======================================");
				}
			}

			else if (c == 11) {
				// for exit
				System.out.println("exited succesfully...");
				break;
			}

			else {
				System.err.println("Try Again");
			}
		}

	}

}
