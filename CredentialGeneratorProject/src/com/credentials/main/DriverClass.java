package com.credentials.main;

import java.util.Scanner;

import com.credentials.model.Employee;
import com.credentials.service.CredentialService;

public class DriverClass {
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter employee details");
		System.out.print("First name: ");
		String firstName = in.nextLine();
		System.out.print("Last name: ");
		String lastName = in.nextLine();

		Employee emp = new Employee(firstName, lastName);
		CredentialService credentials = new CredentialService();

		int option;

		System.out.println("Please enter the department from the following" + "\n1. Technical" + "\n2. Admin"
				+ "\n3. Human Resource" + "\n4. Legal");
		option = in.nextInt();
		switch (option) {
		case 1:
			credentials.showCredentials(emp, "Technical");
			break;
		case 2:
			credentials.showCredentials(emp, "Admin");
			break;
		case 3:
			credentials.showCredentials(emp, "Human Resource");
			break;
		case 4:
			credentials.showCredentials(emp, "Legal");
			break;
		default:
			System.out.println("Department not found.");
			break;
		}
		
		in.close();
	}
}
