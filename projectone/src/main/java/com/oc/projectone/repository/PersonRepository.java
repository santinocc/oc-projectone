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
import com.oc.projectone.dataminer.model.Person;

@Service
public class PersonRepository {

	private static List<Person> persons;

    @PostConstruct
    public void init() throws IOException {
        String filePath = "src/main/resources/data.json";
        byte[] bytesFile = Files.readAllBytes(Paths.get(filePath));

        JsonIterator iter = JsonIterator.parse(bytesFile);
        Any any = iter.readAny();
        Any personAny = any.get("persons");

        //Persons Mapping
        persons = new ArrayList<>();
        personAny.forEach(a -> persons.add(new Person.PersonBuilder().firstName(a.get("firstName").toString())
                .address(a.get("address").toString())
                .city(a.get("city").toString())
                .lastName(a.get("lastName").toString())
                .phone(a.get("phone").toString())
                .zip(a.get("zip").toString())
                .email(a.get("email").toString())
                .build()));

        persons.forEach(p -> System.out.println((p.firstName + ',' + ' ').concat(p.lastName + ',' + ' ').concat(p.address + ',' + ' ').concat(p.city + ',' + ' ').concat(p.phone + ',' + ' ').concat(p.zip + ',' + ' ').concat(p.email + ',' + ' ')));
    }

    public static List<Person> getPersons() {
        return persons;
    }
}
