package com.oc.projectone.model.firestations;


public class ServicedPerson {
	
	public final String firstName;
	public final String lastName;
	public final String address;
	public final String phoneNumber;
	
	public static class ServicedPersonBuilder {
		private String firstName;
		private String lastName;
		private String address;
		private String phoneNumber;
		
		public ServicedPersonBuilder() {
		}
		
		public ServicedPersonBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public ServicedPersonBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		public ServicedPersonBuilder address(String address) {
			this.address = address;
		    return this;
		}
		
		public ServicedPersonBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		    return this;
		}

		
		public ServicedPerson build() {
			return new ServicedPerson(firstName, lastName, address, phoneNumber);
		}
	}
	
	public ServicedPerson(String firstName, String lastName, String address, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

}
