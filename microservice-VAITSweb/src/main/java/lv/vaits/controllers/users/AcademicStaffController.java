package lv.vaits.controllers.users;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.repos.IThesisApplications;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IPersonRepo;
import lv.vaits.services.users.IAcademicStaffServices;
import lv.vaits.services.users.IPersonServices;

@Controller
public class AcademicStaffController {

	@Autowired
	private IAcademicStaffServices academicStaffServices;

	@Autowired
	private IPersonServices personServices;

	@GetMapping("/academicStaff/create")
	public String createAcademicStaffGetFunc(AcademicStaff academicStaff, Model model) {
		model.addAttribute("allPersons", personServices.retrieveAllPersons());
		return "academicStaff-create-page";
	}

	@PostMapping("/academicStaff/create")
	public String createAcademicStaffPostFunc(@Valid AcademicStaff academicStaff, BindingResult result) {
		if (!result.hasErrors()) {
			academicStaffServices.createNewAcademicStaffMember(academicStaff.getName(), academicStaff.getSurname(),
					academicStaff.getPersoncode(), academicStaff.getId_user(), academicStaff.getDegree());
			return "redirect:/academicStaff/showAll";
		} else {
			return "academicStaff-create-page";
		}
	}

	@GetMapping("/academicStaff/update/{id}")
	public String updateAcademicStaffMemberByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("academicStaff", academicStaffServices.retrieveAcademicStaffMemberById(id));
			model.addAttribute("allUsers", personServices.retrieveAllPersons());
			return "academicStaff-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/academicStaff/update/{id}")
	public String updateAcademicStaffMemberByIdPostFunc(@PathVariable("id") Long id, @Valid AcademicStaff academicStaff,
			BindingResult result) {
		if (!result.hasErrors()) {
			try {
				AcademicStaff temp = academicStaffServices.updateAcademicStaffMemberById(id, academicStaff.getName(),
						academicStaff.getSurname(), academicStaff.getPersoncode(), academicStaff.getId_user(),
						academicStaff.getDegree());
				return "redirect:/academicStaff/showAll/" + temp.getIdp();
			} catch (Exception e) {
				return "redirect:/academicStaff/error";
			}
		} else {
			return "academicStaff-update-page";
		}
	}

	@Autowired
	private IAcademicStaffServices iAcademicStaffServices; 

	@GetMapping("/academicStaff/delete/{id}")
	public String deleteAcademicStaffMember(@PathVariable("id") Long id, Model model) {
	    try {
	        iAcademicStaffServices.deleteAcademicStaffMemberById(id);
	        return "redirect:/academicStaff/showAll";
	    } catch (Exception e) {
	        return "error-page";
	    }
	}

	@Autowired
    private LocaleResolver localeResolver;
	
	@GetMapping("/academicStaff/showAll/{id}")
	public String academicStaffByIdFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("academicStaff", academicStaffServices.retrieveAcademicStaffMemberById(id));
			return "academicStaffMember-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/academicStaff/showAll")
	public String allAcademicStaffMembersGetFunc(Model model,
        @RequestParam(value = "lang", required = false) String lang,
        HttpServletRequest request,
        HttpServletResponse response
) {
    if (lang != null) {
        Locale newLocale = new Locale(lang);
        localeResolver.setLocale(request, response, newLocale);
    }
		model.addAttribute("allAcademicStaffMembers", academicStaffServices.retrieveAllAcademicStaffMembers());
		return "academicStaffMembers-all-page";
	}

	@GetMapping("/academicStaff/error")
	public String errorAcademicStaffFunc() {
		return "error-page";
	}

}
