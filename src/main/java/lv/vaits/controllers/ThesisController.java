package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
	//@Autowired
	//private ICommentsServices commentServices;
	
	@GetMapping("/thesis/addNew")
	public String insertThesisGetFunc(Thesis thesis, Model model) {
		model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
		model.addAttribute("allStudents", studentServices.retrieveAllStudents());
		model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaff());
		return "thesis-add-page";
	}

}
