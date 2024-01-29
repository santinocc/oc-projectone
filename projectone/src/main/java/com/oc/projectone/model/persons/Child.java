package com.oc.projectone.model.persons;

public class Child {
	
	public final String firstName;
	public final String lastName;
	public final Integer age;
	
	public static class ChildBuilder {
		private String firstName;
		private String lastName;
		private Integer age;
		
		public ChildBuilder() {
		}
		
		public ChildBuilder firstName(String firstName) {
			this.firstName = firstName;
		    return this;
		}
		
		public ChildBuilder lastName(String lastName) {
			this.lastName = lastName;
		    return this;
		}
		
		public ChildBuilder age(Integer age) {
			this.age = age;
		    return this;
		}
		

		
		public Child build() {
			return new Child(firstName, lastName, age);
		}
	}
	
	public Child(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;

	}

}
