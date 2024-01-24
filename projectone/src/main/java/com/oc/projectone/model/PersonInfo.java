package com.oc.projectone.model;

import java.util.ArrayList;

public class PersonInfo {
	
	public final String firstName;
	public final String lastName;
	public final String address;
	public final Integer age;
	public final String email;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;
	
	public static class PersonInfoBuilder {
		private String firstName;
		private String lastName;
		private String address;
		private Integer age;
		private String email;
		private ArrayList<String> medications;
		private ArrayList<String> allergies;
		
		public PersonInfoBuilder() {
		}
		
		public PersonInfoBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public PersonInfoBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		
		public PersonInfoBuilder address(String address) {
			this.address = address;
		    return this;
		}
		
		public PersonInfoBuilder age(Integer age) {
			this.age = age;
		    return this;
		}
		
		public PersonInfoBuilder email(String email) {
			this.email = email;
		    return this;
		}
		
		public PersonInfoBuilder medications(ArrayList<String> medications) {
			this.medications = medications;
		    return this;
		}
		
		
		public PersonInfoBuilder allergies(ArrayList<String> allergies) {
			this.allergies = allergies;
		    return this;
		}
		
		public PersonInfo build() {
			return new PersonInfo(firstName, lastName, address, age, email, medications, allergies);
		}
	}
	
	public PersonInfo(String firstName, String lastName, String address, Integer age, String email, ArrayList<String> medications, ArrayList<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}

}
