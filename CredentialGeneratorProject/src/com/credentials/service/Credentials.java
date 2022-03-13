package com.credentials.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.credentials.model.Employee;

public class Credentials {
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

	private String generatePassword2() {
		String digits = "0123456789";
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smalls = "abcdefghijklmnopqrstuvwxyz";
		String specials = "$@#&%!";

		Map<Integer, String> constraints = new HashMap<>();
		constraints.put(1, smalls);
		constraints.put(2, caps);
		constraints.put(3, digits);
		constraints.put(4, specials);

		String allchars = digits + caps + smalls + specials;

		Random r = new Random();
		StringBuilder pass = new StringBuilder();

		int passlen = 8;
		int numConstraintVariety = constraints.keySet().size();

		for (int i = 0; i < passlen; i++) {
			char ch;
			int ranindex;

			// make sure at least one char from each of smalls, caps, digits, specials is
			// included
			Set<Integer> usedKeys = new HashSet<>();
			if (i < numConstraintVariety) {
				System.out.println((int) (r.nextDouble() * numConstraintVariety));
				int key = (int) (r.nextDouble() * numConstraintVariety) + 1;
				while (usedKeys.contains(key)) {
					key = (int) (r.nextDouble() * numConstraintVariety) + 1;
				}

				String constraint = constraints.get(key);
				ranindex = (int) (r.nextDouble() * constraint.length());
				ch = constraint.charAt(ranindex);
				pass.append(ch);
				usedKeys.add(key);
			} else {
				// choose remaining chars in pass randomly
				ranindex = (int) (r.nextDouble() * allchars.length());
				ch = allchars.charAt(ranindex);
				pass.append(ch);
			}
		}

		return pass.toString();
	}

	public void showCredentials(Employee emp, String dept) {
		System.out.println("Dear " + emp.getFirstName() + " your generated credentials are as follows"
				+ "\nEmail ----> " + generateEmailAddress(emp, dept) + "\nPassword ----> " + generatePassword());

	}
}
