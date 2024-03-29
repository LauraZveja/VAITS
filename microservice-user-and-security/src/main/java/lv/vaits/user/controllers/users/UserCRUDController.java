package lv.vaits.user.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lv.vaits.user.dto.StudentDTO;
import lv.vaits.user.models.users.User;
import lv.vaits.user.services.users.IUserServices;

@Controller
public class UserCRUDController {

	@Bean
	public PasswordEncoder passwordEncoderSimpleCreate() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Autowired
	private IUserServices userServices;

	@GetMapping("/user/addNewStudent")
	public String insertStudentUserGetFunc(StudentDTO studentDTO) {
		return "student-create-page";
	}

	@PostMapping("/user/addNewStudent")
	public String insertStudentUserPostFunc(@Valid StudentDTO studentDTO, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				User newUser = userServices.createNewUser(studentDTO.getPassword(), studentDTO.getEmail(),
						studentDTO.getUsername());
				return "redirect:http://localhost:8081/student/addNew/" 
						+ newUser.getIdu() + "/" 
						+ studentDTO.getName()+ "/"
						+ studentDTO.getSurname() + "/"
						+ studentDTO.getMatriculaNo() + "/"
						+ studentDTO.getPersoncode();
			} catch (Exception e) {
				return "redirect:/user/error";
			}
		} else {
			return "student-create-page";
		}
	}

	@GetMapping("/user/showAll/{id}")
	public String oneUserByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("User", userServices.retrieveUserById(id));
			return "User-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/user/showAll")
	public String allUsersGetFunc(Model model) {
		model.addAttribute("allUsers", userServices.retrieveAllUsers());
		return "user-all-page";
	}

	@GetMapping("/user/update/{id}")
	public String updateUserByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("user", userServices.retrieveUserById(id));
			return "user-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/user/update/{id}")
	public String updateUserByIdPostFunc(@PathVariable("id") Long id, @Valid User user, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				User temp = userServices.updateUserById(id, user.getPassword(), user.getEmail(), user.getUsername());
				return "redirect:/user/showAll";
			} catch (Exception e) {
				return "redirect:/user/error";
			}
		} else {
			return "user-update-page";
		}
	}

	@GetMapping("/user/remove/{id}")
	public String deleteUserById(@PathVariable("id") Long id, Model model) {
		try {
			userServices.deleteUserById(id);
			model.addAttribute("allUsers", userServices.retrieveAllUsers());
			return "user-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/user/error")
	public String errorUserFunc() {
		return "error-page";
	}
}
