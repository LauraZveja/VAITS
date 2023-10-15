package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.users.Person;
import lv.vaits.repos.users.IPersonRepo;
import lv.vaits.services.users.IPersonServices;

@Service
public class PersonServicesImplementation implements IPersonServices {

	@Autowired
	IPersonRepo personRepo;

	@Override
	public ArrayList<Person> retrieveAllPersons() {
		return (ArrayList<Person>) personRepo.findAll();
	}

}
