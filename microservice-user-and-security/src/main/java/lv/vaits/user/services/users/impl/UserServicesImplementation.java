package lv.vaits.user.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.user.models.users.User;
import lv.vaits.user.repos.users.IUserRepository;
import lv.vaits.user.services.users.IUserServices;

@Service
public class UserServicesImplementation implements IUserServices {

	@Autowired
	IUserRepository userRepository;

	@Override
	public ArrayList<User> retrieveAllUsers() {
		return (ArrayList<User>) userRepository.findAll();
	}

	@Override
	public User createNewUser(String password, String email, String username) throws Exception {
		if (userRepository.findByEmail(email) != null) {
			if (userRepository.findByUsername(username) != null) {
				return userRepository.save(new User(password, email, username));
			} else {
				throw new Exception("Username already taken!");
			}
		} else {
			throw new Exception("E-mail already taken!");
		}
	}

	@Override
	public User retrieveUserById(Long id) throws Exception {
		if (userRepository.existsById(id)) {
			return userRepository.findById(id).get();
		}
		throw new Exception("Wrong id");
	}

	@Override
	public User updateUserById(Long id, String password, String email, String username) throws Exception {
		if (userRepository.existsById(id)) {
			if (userRepository.findByEmail(email) != null) {
				if (userRepository.findByUsername(username) != null) {
					User updateUser = userRepository.findById(id).get();
					updateUser.setPassword(password);
					updateUser.setEmail(email);
					updateUser.setUsername(username);
					return userRepository.save(updateUser);
				} else {
					throw new Exception("Username already taken!");
				}
			} else {
				throw new Exception("E-mail already taken!");
			}
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteUserById(Long id) throws Exception {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}

	}

}
