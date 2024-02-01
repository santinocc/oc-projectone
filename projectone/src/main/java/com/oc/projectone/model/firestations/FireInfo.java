package com.oc.projectone.model.firestations;


public class FireInfo {
	
	public final String firstName;
	public final String lastName;
	public final String phoneNumber;
	public final Integer age;
	public final String[] medications;
	public final String[] allergies;
	
	public static class FireInfoBuilder {
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private Integer age;
		private String[] medications;
		private String[] allergies;
		
		public FireInfoBuilder() {
		}
		
		public FireInfoBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public FireInfoBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		
		public FireInfoBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		    return this;
		}
		
		public FireInfoBuilder age(Integer age) {
			this.age = age;
		    return this;
		}
		
		public FireInfoBuilder medications(String[] medications) {
			this.medications = medications;
		    return this;
		}
		
		
		public FireInfoBuilder allergies(String[] allergies) {
			this.allergies = allergies;
		    return this;
		}
		
		public FireInfo build() {
			return new FireInfo(firstName, lastName, phoneNumber, age, medications, allergies);
		}
	}
	
	public FireInfo(String firstName, String lastName, String phoneNumber, Integer age, String[] medications, String[] allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}
