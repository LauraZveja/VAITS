package lv.vaits.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.services.users.IAcademicStaffServices;
import lv.vaits.services.users.IUserServices;

public class AcademicStaffController {
	
	@Autowired
	private IAcademicStaffServices academicStaffServices;
	
	@Autowired
	private IUserServices userServices;
	
	@GetMapping("/academicStaff/create")
	public String createAcademicStaffGetFunc(AcademicStaff academicStaff, Model model) {
		model.addAttribute("allUsers", userServices.retrieveAllUsers());
		return "academicStaff-create-page";
	}
	
	@PostMapping("/academicStaff/create")
	public String createAcademicStaffPostFunc(@Valid AcademicStaff academicStaff, BindingResult result ) {
		if(!result.hasErrors()) {
			academicStaffServices.createNewAcademicStaffMember(academicStaff.getName(), academicStaff.getSurname(), academicStaff.getPersonCode(), academicStaff.getUser() );
			return "redirect:/academicStaff/showAll";
		}else {
			return "academicStaff-create-page";
		}
	}
	@GetMapping("/academicStaff/update/{id}")
	public String updateAcademicStaffMemberByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("academicStaff", academicStaffServices.retrieveAcademicStaffMemberById(id));
			model.addAttribute("allUsers", userServices.retrieveAllUsers());
			return "academicStaff-update-page";
		}catch(Exception e) {
			return "error-page";
		}
	}
	
	@PostMapping("/academicStaff/update/{id}")
	public String updateAcademicStaffMemberByIdPostFunc(@PathVariable("id") Long id, @Valid AcademicStaff academicStaff, BindingResult result) {
		if(!result.hasErrors()) {
			try {
				AcademicStaff temp = academicStaffServices.updateAcademicStaffMemberById(id, academicStaff.getName(), academicStaff.getSurname(), academicStaff.getPersonCode(), academicStaff.getUser());
				return "redirect:/academicStaff/showAll/" + temp.getIdp();
			}catch (Exception e) {
				return "redirect:/academicStaff-error";
			}
			}else {
				return "academicStaff-update-page";
		}
	}
	
	@GetMapping("/academicStaff/delete/{id}")
	public String deleteAcademicStaffMemberById(@PathVariable("id")Long id, Model model) {
		try {
			academicStaffServices.deleteAcademicStaffMemberById(id);
			model.addAttribute("allAcademicStaffMembers", academicStaffServices.retrieveAllAcademicStaffMembers());
			return "academicStaffMembers-all-page";
		}catch (Exception e) {
			return "error-page";
		}
	}
	
	@GetMapping("/academicStaff/showAll/{id}")
	public String academicStaffByIdFunc(@PathVariable("id")Long id, Model model) {
		try {
			model.addAttribute("academicStaff", academicStaffServices.retrieveAcademicStaffMemberById(id));
			return "academicStaffMember-page";
		}catch(Exception e) {
			return "error-page";
		}
	}
	
	@GetMapping("/academicStaff/showAll")
	public String allAcademicStaffMembersGetFunc(Model model) {
		model.addAttribute("allAcademicStaffMembers", academicStaffServices.retrieveAllAcademicStaffMembers());
		return "academicStaffMembers-all-page";
		
	}
	
	@GetMapping("/thesis/assign/{id}")
	public String assignedThesisGetFunc(@PathVariable("id") Long id, Thesis thesis, Model model) {
		try {
			model.addAttribute("thesisList", academicStaffServices.retrieveThesisByAcademicStaffId(id));
			return "academicStaff-thesis-page";
		}catch (Exception e) {
			return "error-page";
		}
		
	}
	
	@PostMapping("/thesis/assign/{id}/review")
	public String assignReviewToThesis(@PathVariable("id") Long id, @RequestParam("review") String reviewText) {
		Thesis thesis = academicStaffServices.retrieveThesisById(id);
	    thesis.setReview(reviewText);
	    academicStaffServices.saveThesis(thesis);
	    return "redirect:/thesis/assign/";
	}
	
	@PostMapping("/thesis/{thesisId}/review/{reviewId}")
	public String deleteReview(@PathVariable("thesisId") Long thesisId, @PathVariable("reviewId") Long reviewId) {
	    Thesis thesis = academicStaffServices.retrieveThesisById(thesisId);
	    
	    if (thesis.getReview().equals(reviewText)) {
	        thesis.setReview(null);
	        academicStaffServices.saveThesis(thesis);
	        return "redirect:/thesis/assign/";
	    } else {
	        return "error-page";
	    }
	}
	
	@PostMapping("/thesis/{thesisId}/review/{reviewId}")
	public String updateReview(@PathVariable("thesisId") Long thesisId, @PathVariable("reviewId") Long reviewId, @RequestParam("review") String updatedReviewText) {
	    Thesis thesis = academicStaffServices.retrieveThesisById(thesisId);
	    
	    if (thesis.getReview().equals(reviewText)) {
	        thesis.setReview(updatedReviewText);
	        academicStaffServices.saveThesis(thesis);
	        return "redirect:/thesis/assign/";
	    } else {
	        return "error-page";
	    }
	}

	
	
	

}
