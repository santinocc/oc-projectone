package com.oc.projectone.model;

public class Medical {

	public final String firstName;
	public final String lastName;
	public final String birthdate;
	public final String medications;
	public final String allergies;

	public static class MedicalBuilder {
		private String firstName;
		private String lastName;
		private String birthdate;
		private String medications;
		private String allergies;

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

		public MedicalBuilder medications(String medications) {
			this.medications = medications;
			return this;
		}

		public MedicalBuilder allergies(String allergies) {
			this.allergies = allergies;
			return this;
		}

		public Medical build() {
			return new Medical(firstName, lastName, birthdate, medications, allergies);
		}
	}

	private Medical(String firstName, String lastName, String birthdate, String medications, String allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

}
