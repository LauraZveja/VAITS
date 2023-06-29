package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.vaits.models.AcceptanceStatus;
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
				Thesis updatedThesis = thesisServices.updateThesisById(id, thesis.getTitleLv(), thesis.getTitleEn(),
						thesis.getAim(), thesis.getTasks(), thesis.getStudent(), thesis.getSupervisor());
				return "redirect:/thesis/showAll/" + updatedThesis.getIdt();
			} catch (Exception e) {
				return "redirect:/thesis/error";
			}
		} else {
			return "thesis-update-page";
		}
	}

	@GetMapping("/thesis/remove/{id}")
	public String deleteThesisById(@PathVariable("id") Long id, Model model) {
		try {
			// vajag vispirms izdzēst arī komentāru vai mainit constraints datubāzē?
			thesisServices.deleteThesisById(id);
			model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
			return "thesis-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/changeSupervisor/{idThesis}/{idSupervisor}")
	public String changeSupervisorByThesisAndSupervisorIdGetFunc(@PathVariable("idThesis") Long id,
			@PathVariable("idSupervisor") Long idSupervisor, Model model) {
		try {
			model.addAttribute("allThesis", thesisServices.changeSupervisorByThesisAndSupervisorId(id, idSupervisor));
			return "thesis-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/addReviewerByThesisId/{idThesis}/{idReviewer}")
	public String addReviewerByThesisId(@PathVariable("idThesis") Long id, @PathVariable("idReviewer") Long idReviewer,
			Model model) {
		try {
			model.addAttribute("allThesis", thesisServices.addReviewerByThesisId(id, idReviewer));
			return "thesis-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/updateStatus/{id}")
	public String updateThesisStatusGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
			return "thesis-update-status-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/thesis/updateStatus/{id}")
	public String updateThesisStatusPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis,
			BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Thesis updatedThesis = thesisServices.updateThesisStatus(id, thesis.getAccStatus());
				return "redirect:/thesis/showAll/" + updatedThesis.getIdt();
			} catch (Exception e) {
				return "redirect:/thesis/error";
			}
		} else {
			return "error-page";
		}
	}

}
