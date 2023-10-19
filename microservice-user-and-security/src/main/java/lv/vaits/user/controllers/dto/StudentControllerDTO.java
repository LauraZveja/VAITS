package lv.vaits.user.controllers.dto;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.vaits.user.dto.StudentDTO;
import lv.vaits.user.services.users.IUserServices;

@RestController
@RequestMapping("/student")
public class StudentControllerDTO {

	@Autowired
	private IUserServices userServices;

	@GetMapping("/all")
	public ResponseEntity<ArrayList<StudentDTO>> getAllStudents() {
		return new ResponseEntity<ArrayList<StudentDTO>>(userServices.retrieveAllDataForUsers(),
				HttpStatusCode.valueOf(200));
	}

	@PostMapping("/insert")
	public ResponseEntity insertnewGrade(@RequestBody @Valid StudentDTO studentDTO) {
		try {
			userServices.insertStudentByStudentDTO(studentDTO);
			return new ResponseEntity<ArrayList<StudentDTO>>(userServices.retrieveAllDataForUsers(),
					HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<ArrayList<StudentDTO>>(HttpStatusCode.valueOf(404));

	}
}
