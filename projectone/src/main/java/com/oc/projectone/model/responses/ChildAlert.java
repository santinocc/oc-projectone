package com.oc.projectone.model.responses;

import java.util.List;

import com.oc.projectone.model.persons.Adult;
import com.oc.projectone.model.persons.Child;

public class ChildAlert {
	
	public final List<Child> children;
	public final List<Adult> adults;
	
	public static class AdultBuilder {
		private List<Child> children;
		private List<Adult> adults;
		
		public AdultBuilder() {
		}
		
		public AdultBuilder children(List<Child> children) {
			this.children = children;
		    return this;
		}
		
		public AdultBuilder adults(List<Adult> adults) {
			this.adults = adults;
		    return this;
		}
		
		

		
		public ChildAlert build() {
			return new ChildAlert(children, adults);
		}
	}
	
	public ChildAlert(List<Child> children, List<Adult> adults) {
		this.children = children;
		this.adults = adults;

	}

}
