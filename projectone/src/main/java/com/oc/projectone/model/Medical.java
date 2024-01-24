package com.oc.projectone.model;

import java.util.ArrayList;

public class Medical {

	public final String firstName;
	public final String lastName;
	public final String birthdate;
	public final ArrayList<String> medications;
	public final ArrayList<String> allergies;

	public static class MedicalBuilder {
		private String firstName;
		private String lastName;
		private String birthdate;
		private ArrayList<String> medications;
		private ArrayList<String> allergies;

		public MedicalBuilder() {
		}

		public MedicalBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public MedicalBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public MedicalBuilder birthdate(String birthdate) {
			this.birthdate = birthdate;
			return this;
		}

		public MedicalBuilder medications(ArrayList<String> medications) {
			this.medications = medications;
			return this;
		}

		public MedicalBuilder allergies(ArrayList<String> allergies) {
			this.allergies = allergies;
			return this;
		}

		public Medical build() {
			return new Medical(firstName, lastName, birthdate, medications, allergies);
		}
	}

	private Medical(String firstName, String lastName, String birthdate, ArrayList<String> medications, ArrayList<String> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

}
