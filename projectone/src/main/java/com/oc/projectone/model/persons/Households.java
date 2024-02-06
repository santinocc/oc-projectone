package com.oc.projectone.model.persons;

import java.util.List;

public class Households {
	
	public final List<Household> households;
	
	public static class HouseholdsBuilder {
		private List<Household> households;
		
		public HouseholdsBuilder() {
		}
		
		public HouseholdsBuilder households(List<Household> households) {
			this.households = households;
		    return this;
		}
		
		public Households build() {
			return new Households(households);
		}
	}
	
	public Households(List<Household> households) {
		this.households = households;
	}

}
