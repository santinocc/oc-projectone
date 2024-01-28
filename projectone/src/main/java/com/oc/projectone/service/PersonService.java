package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.Child;
import com.oc.projectone.model.Medical;
import com.oc.projectone.model.PersonInfo;

public interface PersonService {
	
	List<PersonInfo> getPersonInfo(String firstName, String lastName);
	
	List<String> getCommunityEmail(String city);
	
	Integer calculateAge(String birthdate);
	
	Medical getMedical(String firstName, String lastName, List<Medical> medicals);
	
	List<Child> getChildrenList(String address);

}
