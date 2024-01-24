package lv.vaits.user.services.users;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.validation.Valid;
import lv.vaits.user.dto.StudentDTO;
import lv.vaits.user.models.users.Authorities;
import lv.vaits.user.models.users.User;

public interface IUserServices {
	
	ArrayList<StudentDTO> retrieveAllDataForUsers();

	ArrayList<User> retrieveAllUsers();

	User createNewUser(String password, String email, String username) throws Exception;

	User retrieveUserById(Long id) throws Exception;

	User updateUserById(Long id, String password, String email, String username) throws Exception;

	void deleteUserById(Long id) throws Exception;

	void insertStudentByStudentDTO(@Valid StudentDTO studentDTO);

}
