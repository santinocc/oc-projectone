package com.oc.projectone.service;

import java.util.List;

import com.oc.projectone.model.PersonInfo;

public interface PersonService {
	
	List<String> getCommunityEmail(String city);
	
	List<PersonInfo> getPersonInfo(String firstName, String lastName);

}
