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

	private static List<Medical> medical;

    @PostConstruct
    public void init() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(Paths.get(filePath));

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();
        Any medicalAny = any.get("medicalrecords");

        //Medical Records Mapping
        medical = new ArrayList<>();
    	medicalAny.forEach(medicalRecord -> {System.out.println(medicalRecord.get("firstName").toString().concat(medicalRecord.get("lastName").toString())
    			.concat(medicalRecord.get("birthdate").toString()));
    			Any medications = medicalRecord.get("medications");
    			medications.forEach(a -> System.out.println(a.toString()));
    			
    			Any allergies = medicalRecord.get("allergies");
    			allergies.forEach(a -> System.out.println(a.toString()));
    	});
    }

    public static List<Medical> getMedicalRecords() {
        return medical;
    }
}
