package lv.vaits.controllers.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.Student;
import lv.vaits.services.ICourseServices;
import lv.vaits.services.users.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

	@Autowired
	private IStudentServices studentServices;

	@Autowired
	private ICourseServices courseServices;

	@Autowired
	private IUserServices userServices;

	@Autowired
	private IAcademicStaffServices academicStaffServices;

	@GetMapping("/student/addNew")
	public String insertStudentGetFunc(Student student, Model model) {
		model.addAttribute("allUsers", userServices.retrieveAllUsers());
		return "student-add-page";
	}

	@PostMapping("/student/addNew")
	public String insertStudentPostFunc(@Valid Student student, BindingResult result) {
		if (!result.hasErrors()) {
			studentServices.createNewStudent(student.getName(), student.getSurname(), student.getPersoncode(),
					student.getUser(), student.getMatriculaNo(), student.isFinancialDebt());
			return "redirect:/student/showAll";
		} else {
			return "student-add-page";
		}
	}

	@GetMapping("/student/showAll/{id}")
	public String oneStudentByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("student", studentServices.retrieveStudentById(id));
			return "student-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/showAll")
	public String allStudentsGetFunc(Model model) {
		model.addAttribute("allStudents", studentServices.retrieveAllStudents());
		return "student-all-page";
	}

	@GetMapping("/student/update/{id}")
	public String updateStudentByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("student", studentServices.retrieveStudentById(id));
			model.addAttribute("allUsers", userServices.retrieveAllUsers());
			return "student-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/student/update/{id}")
	public String updateStudentByIdPostFunc(@PathVariable("id") Long id, @Valid Student student, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Student temp = studentServices.updateStudentById(id, student.getName(), student.getSurname(),
						student.getPersoncode(), student.getUser(), student.getMatriculaNo(),
						student.isFinancialDebt());
				return "redirect:/student/showAll/" + temp.getIdp();
			} catch (Exception e) {
				return "redirect:/student/error";
			}
		} else {
			return "student-update-page";
		}
	}

	@GetMapping("/student/remove/{id}")
	public String deleteStudentById(@PathVariable("id") Long id, Model model) {
		try {
			studentServices.deleteStudentById(id);
			model.addAttribute("allStudents", studentServices.retrieveAllStudents());
			return "student-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/showAllDebtCourses/{id}")
	public String allDebtCoursesByStudentsIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("student", studentServices.retrieveStudentById(id));
			model.addAttribute("allDebtCourses", studentServices.retrieveAllDebtCoursesByStudentId(id));
			return "student-debtors-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	@GetMapping("/student/showAllDebtCourses/add/{studentid}")
	public String addDebtCourseByStudentsIdGetFunc(@PathVariable("studentid") Long studentid, Model model) {
		try {
			model.addAttribute("studentid", studentid);
			model.addAttribute("studentDebtCourses", studentServices.retrieveAllDebtCoursesByStudentId(studentid));
			model.addAttribute("allDebtCourses", courseServices.selectAllCourse());
			return "student-debtors-add-page";
		} catch (Exception e) {
			return "redirect:/student/error";
		}
	}

	@PostMapping("/student/showAllDebtCourses/add/{studentid}")
	public String addDebtCourseByStudentsIdPostFunc(@PathVariable("studentid") Long studentid,
			@RequestParam("debtCourses") List<Long> debtCourseIds) throws Exception {
		studentServices.addDebtCourseByStudentId(studentid, debtCourseIds);
		return "redirect:/student/showAllDebtCourses/" + studentid;
	}

	@GetMapping("/student/showAllDebtCourses/remove/{studentid}")
	public String deleteDebtCourseByStudentIdGetFunc(@PathVariable("studentid") Long studentid, Model model) {
		try {
			model.addAttribute("studentid", studentid);
			model.addAttribute("studentDebtCourses", studentServices.retrieveAllDebtCoursesByStudentId(studentid));
			return "student-debtors-remove-page";
		} catch (Exception e) {
			return "redirect:/student/error";
		}
	}

	@PostMapping("/student/showAllDebtCourses/remove/{studentid}")
	public String deleteDebtCourseByStudentIdPostFunc(@PathVariable("studentid") Long studentid,
			@RequestParam("debtCourses") List<Long> debtCourseIds) throws Exception {
		studentServices.removeDebtCourseByStudentId(studentid, debtCourseIds);
		return "redirect:/student/showAllDebtCourses/" + studentid;
	}

	@GetMapping("/student/thesis/{id}")
	public String studentThesisGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesisList", studentServices.retrieveStudentThesisByStudentId(id));
			return "student-thesis-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/thesis/addNew/{id}")
	public String insertStudentThesisGetFunc(@PathVariable("id") Long id, Thesis thesis, Model model) {
		model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaffMembers());
		return "student-thesis-add-page";
	}

	@PostMapping("/student/thesis/addNew/{id}")
	public String insertStudentThesisPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis, BindingResult result)
			throws Exception {
		if (!result.hasErrors()) {
			studentServices.submitThesisByStudentId(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAim(),
					thesis.getTasks(), id, thesis.getSupervisor());
			return "redirect:/student/thesis/" + id;
		} else {
			return "error-page";
		}
	}

	@GetMapping("/student/error")
	public String errorStudentFunc() {
		return "error-page";
	}

	@GetMapping("student/export")
	public ResponseEntity<InputStreamResource> exportStudentToExcel() throws IOException {
		Workbook workbook = studentServices.exportStudentsToExcel();

		File tempFile = File.createTempFile("students", ".xlsx");

		FileOutputStream fos = new FileOutputStream(tempFile);

		workbook.write(fos);
		fos.close();

		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", "attachment; filename=students.xlsx");

		return ResponseEntity
				.ok()
				.headers(headers)
				.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
				.body(new InputStreamResource(new FileInputStream(tempFile)));
	}

	@PostMapping("/student/import")
	public ResponseEntity<String> importStudents(@RequestParam("file") MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Please select an Excel file to upload.");
		}

		try {
			studentServices.importStudentsFromExcel(file.getInputStream());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/student/showAll");

			return new ResponseEntity<>(headers, HttpStatus.FOUND);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during import.");
		}
	}


}
