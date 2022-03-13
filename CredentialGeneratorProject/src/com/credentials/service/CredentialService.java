package com.credentials.service;

import java.util.Random;

import com.credentials.model.Employee;

public class CredentialService {
	private String generateEmailAddress(Employee emp, String dept) {
		String firstName = emp.getFirstName().toLowerCase();
		String lastName = emp.getLastName().toLowerCase();

		// remove inner whitespaces
		dept = dept.replaceAll("\\s", "").toLowerCase();

		return firstName + lastName + "@" + dept + ".company.com";
	}

	private String generatePassword() {
		String digits = "0123456789";
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smalls = "abcdefghijklmnopqrstuvwxyz";
		String specials = "$@#&%!";
		String allchars = digits + caps + smalls + specials;

		Random r = new Random();
		StringBuilder pass = new StringBuilder();
		int passlen = 8;

		for (int i = 0; i < passlen; i++) {
			int ranindex = r.nextInt(allchars.length());
			pass.append(allchars.charAt(ranindex));
		}

		return pass.toString();
	}

	public void showCredentials(Employee emp, String dept) {
		System.out.println("Dear " + emp.getFirstName() + " your generated credentials are as follows"
				+ "\nEmail ----> " + generateEmailAddress(emp, dept) + "\nPassword ----> " + generatePassword());
	}
}
