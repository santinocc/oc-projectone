package com.oc.projectone.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.oc.projectone.model.Medical;

@Service
public class MedicalRepository {

	private static List<Medical> medicals; //This is for ALL MedicalRecords
	List<String> medicationsList;
	List<String> allergiesList;
	String[] medicationsArray;
	String[] allergiessArray;

    @PostConstruct
    public void init() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(Paths.get(filePath));

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();
        Any medicalAny = any.get("medicalrecords");

//        System.out.println(medicalAny.get("medications"));
        
        //Medical Records Mapping
        
        medicals = new ArrayList<>();
		
//    	medicalAny.forEach(medical -> medicals.add(new Medical.MedicalBuilder().firstName(medical.get("firstName").toString())
//    			.lastName(medical.get("lastName").toString())
//    			.birthdate(medical.get("birthdate").toString())
//
//    			
//    			.medications(medical.get("medications")) //TODO: Figure out how to add/build Array for the 'medications' and 'allergies'
//    			.allergies(medical.get("allergies"))
//    			.build()));
        
    	medicalAny.forEach(medicalRecord -> {
    		
			Any medications = medicalRecord.get("medications");
			medications.forEach(a -> medicationsList.add(a.toString()));
			
			Any allergies = medicalRecord.get("allergies");
			allergies.forEach(a -> allergiesList.add(a.toString()));
			
			medicationsArray = medicationsList.toArray(new String[medicationsList.size()]);
			allergiessArray = allergiesList.toArray(new String[allergiesList.size()]);
			
			medicals.add(new Medical.MedicalBuilder().firstName(medicalRecord.get("firstName").toString())
	    			.lastName(medicalRecord.get("lastName").toString())
	    			.birthdate(medicalRecord.get("birthdate").toString())
					.medications(medicationsArray)
					.allergies(allergiesArray)
					build());
    	});
      
    	
//    	medicals.forEach(p -> System.out.println((p.firstName + ',' + ' ').concat(p.lastName + ',' + ' ').concat(p.birthdate + ',' + ' ').concat(p.medications + ',' + ' ').concat(p.allergies + ',' + ' ')));
    }

    public static List<Medical> getMedicalRecords() {
        return medicals;
    }
}
