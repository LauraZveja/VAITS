package lv.vaits.controllers.users;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.Student;
import lv.vaits.services.ICourseServices;
import lv.vaits.services.users.IStudentServices;

@Controller
public class StudentController {

	@Autowired
	private IStudentServices studentServices;

	@Autowired
	private ICourseServices courseServices;

	@GetMapping("/student/addNew")
	public String insertStudentGetFunc(Student student) {
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
		model.addAttribute("alldStudents", studentServices.retrieveAllStudents());
		return "student-all-page";
	}

	@GetMapping("/student/update/{id}")
	public String updateStudentByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("student", studentServices.retrieveStudentById(id));
			return "student-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/student/update/{id}")
	public String updateStudentByIdPostFunc(@PathVariable("id") int id, @Valid Student student, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Student temp = studentServices.updateStudentById(student.getIdp(), student.getName(),
						student.getSurname(), student.getPersoncode(), student.getUser(), student.getMatriculaNo(),
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
			model.addAttribute("alldDrivers", studentServices.retrieveAllStudents());
			return "student-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/showAllDebtCourses/{id}")
	public String allDebtCoursesByStudentsIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("allDebtCourses", studentServices.retrieveAllDebtCoursesByStudentId(id));
			return "debtCourse-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/showAllDebtCourses/add/{studentid}")
	public String addDebtCourseByStudentsIdGetFunc(@PathVariable("studentid") Long studentid, Long courseId,
			Model model) {
		try {
			model.addAttribute("studentid", studentid);
			model.addAttribute("allCourses", courseServices.selectAllCourse());
			return "debtCourse-add-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/student/error";
		}
	}

	@PostMapping("/student/showAllDebtCourses/add/{studentid}")
	public String addDebtCourseByStudentsIdPostFunc(@PathVariable("studentid") Long studentid, @Valid Long courseId,
			BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			studentServices.addDebtCourseByStudentIdAndCourseId(studentid, courseId);
			return "redirect:/student/debtors/" + studentid;
		} else {
			return "redirect:/student/error";
		}
	}

	@GetMapping("/student/thesis/{id}")
	public String studentThesisGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesis", studentServices.retrieveStudentThesisByStudentId(id));
			return "student-thesis-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/student/thesis/addNew")
	public String insertStudentThesisGetFunc(Thesis thesis, Model model) {
		model.addAttribute("allStudents", studentServices.retrieveAllStudents());
		return "student-thesis-add-page";
	}

	@PostMapping("/student/thesis/addNew")
	public String insertStudentThesisPostFunc(@Valid Thesis thesis, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			studentServices.submitThesisByStudentId(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAim(),
					thesis.getTasks(), thesis.getStudent().getIdp(), thesis.getSupervisor());
			return "redirect:/student/thesis/all";
		} else {
			return "error-page";
		}
	}

	@GetMapping("/student/thesis/comments")
	public String showCommentsForThesisGetFunc(@RequestParam("studentId") Long studentId, Model model)
			throws Exception {
		ArrayList<Student> allStudents = studentServices.retrieveAllStudents();
		model.addAttribute("allStudents", allStudents);
		Student selectedStudent = null;
		for (Student student : allStudents) {
			if (student.getIdp() == studentId) {
				selectedStudent = student;
				break;
			}
		}
		if (selectedStudent == null) {
			throw new Exception("Invalid student ID");
		}
		model.addAttribute("thesis", studentServices.retrieveStudentThesisByStudentId(selectedStudent.getIdp()));
		return "student-thesis-comments-page";
	}

	@GetMapping("/student/error")
	public String errorStudentFunc() {
		return "error-page";
	}

}
