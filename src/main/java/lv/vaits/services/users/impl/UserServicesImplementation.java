package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.users.User;
import lv.vaits.repos.users.IUserRepo;
import lv.vaits.services.users.IUserServices;

@Service
public class UserServicesImplementation implements IUserServices {

	@Autowired
	IUserRepo userRepo;

	@Override
	public ArrayList<User> retrieveAllUsers() {
		return (ArrayList<User>) userRepo.findAll();
	}

}
