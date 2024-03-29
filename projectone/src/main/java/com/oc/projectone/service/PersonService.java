package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.Person;
import com.oc.projectone.model.persons.Adult;
import com.oc.projectone.model.persons.Child;
import com.oc.projectone.model.responses.ChildAlert;
import com.oc.projectone.model.responses.PersonInfo;

public interface PersonService {
	
	List<PersonInfo> getPersonInfo(String firstName, String lastName);
	
	List<String> getCommunityEmail(String city);
	
	Integer calculateAge(String birthdate);
	
	List<Child> getChildrenList(String address);
	
	List<Adult> getAdultsList(String address);
	
	ChildAlert getChildAlert(String address);
	
	public Person addPerson(Person person);
	
	public Person deletePerson(String firstName, String lastName);
	
	public boolean updatePerson(Person personUpdate);

}
