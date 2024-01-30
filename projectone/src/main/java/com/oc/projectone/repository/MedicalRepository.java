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
//TRIAL1		
//    	medicalAny.forEach(medical -> medicals.add(new Medical.MedicalBuilder().firstName(medical.get("firstName").toString())
//    			.lastName(medical.get("lastName").toString())
//    			.birthdate(medical.get("birthdate").toString())
//	
//    			.medications(medical.get("medications")) //TODO: Figure out how to add/build Array for the 'medications' and 'allergies'
//    			.allergies(medical.get("allergies"))
//    			.build()));
        
        
//TRIAL2        
//    	medicalAny.forEach(medicalRecord -> 
//    		
//    		Medical medical = JsonIterator.deserialize(medicalRecord, Medical.class);
//    		
//    		medicals.add(medical));
    		
//TRIAL3  
    	medicalAny.forEach(medicalRecord -> {
    	
    		List<String> medicationsList = new ArrayList<String>();
    		List<String> allergiesList = new ArrayList<String>();
    		
			Any medications = medicalRecord.get("medications");
			medications.forEach(a -> medicationsList.add(a.toString()));
			
			Any allergies = medicalRecord.get("allergies");
			allergies.forEach(a -> allergiesList.add(a.toString()));
    	
			String[] medicationsArray = medicationsList.toArray(new String[medicationsList.size()]);
			String[] allergiesArray = allergiesList.toArray(new String[allergiesList.size()]);
			
			medicals.add(new Medical.MedicalBuilder()
					.firstName(medicalRecord.get("firstName").toString())
					.lastName(medicalRecord.get("lastName").toString())
					.birthdate(medicalRecord.get("birthdate").toString())
					.medications(medicationsArray)
					.allergies(allergiesArray)
					.build());
    	
    	});
    	
//    	System.out.println(medicalRecord));
//    	medicals.add(new Medical.MedicalBuilder().firstName(medicalRecord.get("firstName").toString())
//    			.lastName(medicalRecord.get("lastName").toString())
//    			.birthdate(medicalRecord.get("birthdate").toString())
//    			.medications(medicalRecord.get("medications").toString().split(","))
//    			.allergies(medicalRecord.get("allergies").toString().split(","))
//    			.build()));
  
//TRIAL4    	
//    	{
//			Any medications = medicalRecord.get("medications");
//			medications.forEach(a -> medicationsList.add(a.toString()));
//			
//			Any allergies = medicalRecord.get("allergies");
//			allergies.forEach(a -> allergiesList.add(a.toString()));
//			
//			String[] medicationsArray = medicationsList.toArray(new String[medicationsList.size()]);
//			String[] allergiesArray = allergiesList.toArray(new String[allergiesList.size()]);
//			
//			medicals.add(new Medical.MedicalBuilder().firstName(medicalRecord.get("firstName").toString())
//	    			.lastName(medicalRecord.get("lastName").toString())
//	    			.birthdate(medicalRecord.get("birthdate").toString())
//					.medications(medicationsArray)
//					.allergies(allergiesArray)
//					build())
//			});
    	
      System.out.println(medicals);
    	
//TRIAL5        
//    	medicalAny.forEach(medicalRecord -> 
//		
//    	try {
//    		byte[] medicalFile = Files.readAllBytes(Paths.get(medicalRecord.toString()));
//    		
//    		JsonIterator iter1 = JsonIterator.parse(medicalFile);
//    		iter1.readObjectStart();
//    		String string1 = iter.readString();
//            iter.readObjectFieldAsHash(); // skip field name
//            String string2 = iter.readString();
//            iter.readObjectFieldAsHash(); // skip field name
//            String string3 = iter.readString();
//            iter.readObjectFieldAsHash(); // skip field name
//            String[] array1 = iter.read(String[].class);
//            iter.readObjectFieldAsHash(); // skip field name
//            String[] array2 = iter.read(String[].class);
//            iter.readObjectEnd();
//            return new Medical(string1, string2, string3, array1, array2);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }

//    	medicalAny.forEach(medicalRecord -> {System.out.println(medicalRecord.get("firstName").toString().concat(medicalRecord.get("lastName").toString())
//    			.concat(medicalRecord.get("birthdate").toString()));
//    			Any medications = medicalRecord.get("medications");
//    			medications.forEach(a -> System.out.println(a.toString()));
//    			
//    			Any allergies = medicalRecord.get("allergies");
//    			allergies.forEach(a -> System.out.println(a.toString()));
//    	});
//    }
//    	
//    	});
//    	medicals.forEach(p -> System.out.println((p.firstName + ',' + ' ').concat(p.lastName + ',' + ' ').concat(p.birthdate + ',' + ' ').concat(p.medications + ',' + ' ').concat(p.allergies + ',' + ' ')));
    }

    public static List<Medical> getMedicalRecords() {
        return medicals;
    }
}
