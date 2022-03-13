package com.credentials.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.credentials.model.Employee;

public class CredentialService {
	private static String COMPANY_URL = "abc.com";

	private String generateEmailAddress(Employee emp, String dept) {
		String firstName = emp.getFirstName().toLowerCase();
		String lastName = emp.getLastName().toLowerCase();

		// remove inner whitespaces
		dept = dept.replaceAll("\\s", "").toLowerCase();

		return firstName + lastName + "@" + dept + "." + COMPANY_URL;
	}

	private String generatePassword() {
		String digits = "0123456789";
		String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smalls = "abcdefghijklmnopqrstuvwxyz";
		String symbols = "~`!@#$%^&*()-_=+[{]}\\\\|;:\\'\\\",<.>/?";
		String allchars = digits + caps + smalls + symbols;

		Map<Integer, String> constraints = new HashMap<>();
		constraints.put(0, digits);
		constraints.put(1, caps);
		constraints.put(2, smalls);
		constraints.put(3, symbols);

		Random r = new Random();
		StringBuilder pass = new StringBuilder();
		int passlen = 8;

		for (int i = 0; i < passlen; i++) {
			int ranindex;
			char ch;
			// include at least one char from each digits, caps, smalls and symbols
			if (i < constraints.keySet().size()) {
				String constraint = constraints.get(i);
				ranindex = r.nextInt(constraint.length());
				ch = constraint.charAt(ranindex);
				pass.append(ch);
			} else {
				ranindex = r.nextInt(allchars.length());
				ch = allchars.charAt(ranindex);
				pass.append(ch);
			}
		}

		return randomize(pass.toString());
	}

	public void showCredentials(Employee emp, String dept) {
		System.out.println("Dear " + emp.getFirstName() + " your generated credentials are as follows"
				+ "\nEmail ----> " + generateEmailAddress(emp, dept) + "\nPassword ----> " + generatePassword());
	}

	/**
	 * Reference:<br>
	 * https://stackoverflow.com/questions/3316674/how-to-shuffle-characters-in-a-string-without-using-collections-shuffle
	 * 
	 */
	private static String randomize(String s) {
		List<String> chars = Arrays.asList(s.split(""));
		Collections.shuffle(chars);
		StringBuilder rearrangedStr = new StringBuilder();
		for (String ch : chars) {
			rearrangedStr.append(ch);
		}
		return rearrangedStr.toString();
	}
}
