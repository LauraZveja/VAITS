package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.services.ICommentsServices;
import lv.vaits.services.IThesisServices;
import lv.vaits.services.users.IAcademicStaffServices;
import lv.vaits.services.users.IStudentServices;

@Controller
public class ThesisController {

	@Autowired
	private IStudentServices studentServices;

	@Autowired
	private IAcademicStaffServices academicStaffServices;

	@Autowired
	private IThesisServices thesisServices;

	// @Autowired
	// private ICommentsServices commentServices;

	@GetMapping("/thesis/addNew")
	public String insertThesisGetFunc(Thesis thesis, Model model) {
		model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
		model.addAttribute("allStudents", studentServices.retrieveAllStudents());
		model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaff());
		return "thesis-add-page";
	}

	@PostMapping("/thesis/addNew")
	public String insertThesisPostFunc(@Valid Thesis thesis, BindingResult result) {
		if (!result.hasErrors()) {
			thesisServices.createNewThesis(thesis.getTitleLv(), thesis.getTitleEn(), thesis.getAim(), thesis.getTasks(),
					thesis.getStudent(), thesis.getSupervisor());
			return "redirect:/thesis/showAll";
		} else {
			return "thesis-add-page";
		}
	}

	@GetMapping("/thesis/showAll")
	public String allThesisGetFunc(Model model) {
		model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
		return "thesis-all-page";
	}

	@GetMapping("/thesis/showAll/{id}")
	public String oneThesisByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
			return "thesis-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/update/{id}")
	public String updateThesisByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
			model.addAttribute("allStudents", studentServices.retrieveAllStudents());
			model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaff());
			return "thesis-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/thesis/update/{id}")
	public String updateThesisByIdPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Thesis temp = thesisServices.updateThesisById(id, thesis.getTitleLv(), thesis.getTitleEn(),
						thesis.getAim(), thesis.getTasks(), thesis.getStudent(), thesis.getSupervisor());
				return "redirect:/thesis/showAll/" + temp.getIdt();
			} catch (Exception e) {
				return "redirect:/thesis/error";
			}
		} else {
			return "thesis-update-page";
		}
	}

}
