package com.oc.projectone.model.persons;

public class Household {
	
	public final String firstName;
	public final String lastName;
	public final String phone;
	public final Integer age;
	public final String[] medications;
	public final String[] allergies;
	
	public static class HouseholdBuilder {
		private String firstName;
		private String lastName;
		private String phone;
		private Integer age;
		private String[] medications;
		private String[] allergies;
		
		public HouseholdBuilder() {
		}
		
		public HouseholdBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public HouseholdBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		public HouseholdBuilder phone(String phone) {
			this.phone = phone;
		    return this;
		}
		
		public HouseholdBuilder age(Integer age) {
			this.age = age;
		    return this;
		}
		
		public HouseholdBuilder medications(String[] medications) {
			this.medications = medications;
		    return this;
		}
		
		public HouseholdBuilder allergies(String[] allergies) {
			this.allergies = allergies;
		    return this;
		}
		
		public Household build() {
			return new Household(firstName, lastName, phone, age, medications, allergies);
		}
	}
	
	public Household(String firstName, String lastName, String phone, Integer age, String[] medications, String[] allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}
