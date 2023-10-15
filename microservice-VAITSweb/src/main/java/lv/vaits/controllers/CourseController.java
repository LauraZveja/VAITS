package lv.vaits.controllers;

import java.util.List;

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
import lv.vaits.services.ICourseServices;
import lv.vaits.services.users.IStudentServices;

@Controller
public class CourseController {

	@Autowired
	private ICourseServices courseServices;

	@Autowired
	private IStudentServices studentServices;

	@GetMapping("/course/addNew")
	public String insertCourseGetFunc(Course course) {
		return "course-add-page";
	}

	@PostMapping("/course/addNew")
	public String insertCoursePostFunc(@Valid Course course, BindingResult result) {
		if (!result.hasErrors()) {
			courseServices.createNewCourse(course.getTitle(), course.getCreditPoints());
			return "redirect:/course/showAll";
		} else {
			return "course-add-page";
		}
	}

	@GetMapping("/course/showAll/{id}")
	public String oneCourseByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("course", courseServices.retrieveCourseById(id));
			return "course-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/course/showAll")
	public String allCoursesGetFunc(Model model) {
		model.addAttribute("allCourses", courseServices.selectAllCourse());
		return "course-all-page";
	}

	@GetMapping("/course/update/{id}")
	public String updateCourseByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("course", courseServices.retrieveCourseById(id));
			return "course-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/course/update/{id}")
	public String updateCourseByIdPostFunc(@PathVariable("id") Long id, @Valid Course course, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Course temp = courseServices.updateCourseById(id, course.getTitle(), course.getCreditPoints());
				return "redirect:/course/showAll/" + temp.getIdc();
			} catch (Exception e) {
				return "redirect:/course/error";
			}
		} else {
			return "course-update-page";
		}
	}

	@GetMapping("/course/remove/{id}")
	public String deleteCourseById(@PathVariable("id") Long id, Model model) {
		try {
			courseServices.deleteCourseById(id);
			model.addAttribute("allCourses", courseServices.selectAllCourse());
			return "course-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/course/showAllDebtors/{id}")
	public String allDebtorsByCourseIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("course", courseServices.retrieveCourseById(id));
			model.addAttribute("allDebtors", courseServices.retrieveAllStudentDebtsByCourseId(id));
			return "course-debtors-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	@GetMapping("/course/showAllDebtors/add/{courseid}")
	public String addDebtorByCourseIdGetFunc(@PathVariable("courseid") Long courseid, Model model) {
		try {
			model.addAttribute("courseid", courseid);
			model.addAttribute("allDebtors", courseServices.retrieveAllStudentDebtsByCourseId(courseid));
			model.addAttribute("allStudents", studentServices.retrieveAllStudents());
			return "course-debtors-add-page";
		} catch (Exception e) {
			return "redirect:/course/error";
		}
	}

	@PostMapping("/course/showAllDebtors/add/{courseid}")
	public String addDebtorByCourseIdPostFunc(@PathVariable("courseid") Long courseid,
			@RequestParam("Debtors") List<Long> debtorsId) throws Exception {
		courseServices.addStudentDebtByCourseId(courseid, debtorsId);
		return "redirect:/course/showAllDebtors/" + courseid;
	}

	@GetMapping("/course/showAllDebtors/remove/{courseid}")
	public String deleteDebtorsByCourseIdGetFunc(@PathVariable("courseid") Long courseid, Model model) {
		try {
			model.addAttribute("courseid", courseid);
			model.addAttribute("allDebtors", courseServices.retrieveAllStudentDebtsByCourseId(courseid));
			return "course-debtors-remove-page";
		} catch (Exception e) {
			return "redirect:/course/error";
		}
	}

	@PostMapping("/course/showAllDebtors/remove/{courseid}")
	public String deleteDebtorsByCourseIdPostFunc(@PathVariable("courseid") Long courseid,
			@RequestParam("Debtors") List<Long> debtorsId) throws Exception {
		courseServices.removeStudentDebtByCourseId(courseid, debtorsId);
		return "redirect:/course/showAllDebtors/" + courseid;
	}

	@GetMapping("/course/error")
	public String errorAcademicStaffFunc() {
		return "error-page";
	}
}
