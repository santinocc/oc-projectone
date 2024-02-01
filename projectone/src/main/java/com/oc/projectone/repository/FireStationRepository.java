package com.oc.projectone.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.oc.projectone.model.FireStation;

@Service
public class FireStationRepository {

	private static List<FireStation> fireStations;

    @PostConstruct
    public void init() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(Paths.get(filePath));

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();
     	Any fireStationAny = any.get("firestations");

        //FireStations Mapping
     	Map<String, FireStation> fireStationMap = new HashMap<>();
     	fireStationAny.forEach(anyStation -> { 
    		fireStationMap.compute(anyStation.get("station").toString(), 
    			    (k, v) -> v == null ?
    			    		new FireStation(anyStation.get("station").toString()).addAddress(anyStation.get("address").toString()) :
    			    	    v.addAddress(anyStation.get("address").toString()));
    	});

    	fireStations = fireStationMap.values().stream().collect(Collectors.toList());
    	fireStations.forEach(firestation -> System.out.println("Firestation " + firestation.toString()));

    }

    public static List<FireStation> getFireStations() {
        return fireStations;
    }
}
