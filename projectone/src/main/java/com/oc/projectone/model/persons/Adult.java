package com.oc.projectone.model.persons;

public class Adult {
	
	public final String firstName;
	public final String lastName;
	
	public static class AdultBuilder {
		private String firstName;
		private String lastName;
		
		public AdultBuilder() {
		}
		
		public AdultBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public AdultBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		

		
		public Adult build() {
			return new Adult(firstName, lastName);
		}
	}
	
	public Adult(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;

	}

}
