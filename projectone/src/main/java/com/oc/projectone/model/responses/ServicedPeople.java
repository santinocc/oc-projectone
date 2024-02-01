package com.oc.projectone.model.responses;

import java.util.List;

import com.oc.projectone.model.firestations.ServicedPerson;

public class ServicedPeople {
	
	public final String station;
	public final List<ServicedPerson> servicedPersons;
	public final Integer adults;
	public final Integer children;
	
	public static class ServicedPeopleBuilder {
		private String station;
		private List<ServicedPerson> servicedPersons;
		private Integer adults;
		private Integer children;
		
		public ServicedPeopleBuilder() {
		}
		
		public ServicedPeopleBuilder station(String station) {
			this.station = station;
		    return this;
		}
		
		public ServicedPeopleBuilder ServicedPersons(List<ServicedPerson> servicedPersons) {
			this.servicedPersons = servicedPersons;
		    return this;
		}
		
		public ServicedPeopleBuilder adults(Integer adults) {
			this.adults = adults;
		    return this;
		}
		
		public ServicedPeopleBuilder children(Integer children) {
			this.children = children;
		    return this;
		}	

		
		public ServicedPeople build() {
			return new ServicedPeople(station, servicedPersons, adults, children);
		}
	}
	
	public ServicedPeople(String station, List<ServicedPerson> servicedPersons, Integer adults, Integer children) {
		this.station = station;
		this.servicedPersons = servicedPersons;
		this.adults = adults;
		this.children = children;
	}

}
