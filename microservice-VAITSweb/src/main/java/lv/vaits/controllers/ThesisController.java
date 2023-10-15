package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lv.vaits.models.Thesis;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.services.IThesisServices;
import lv.vaits.services.users.IAcademicStaffServices;
import lv.vaits.services.users.IStudentServices;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Controller
public class ThesisController {

	@Autowired
	private IStudentServices studentServices;

	@Autowired
	private IAcademicStaffServices academicStaffServices;

	@Autowired
	private IThesisServices thesisServices;

	@Autowired
	private IThesisRepo thesisRepo;

	@GetMapping("/thesis/addNew")
	public String insertThesisGetFunc(Thesis thesis, Model model) {
		model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
		model.addAttribute("allStudents", studentServices.retrieveAllStudents());
		model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaffMembers());
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
		model.addAttribute("activeThesisList", thesisServices.retrieveActiveTheses());
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
			model.addAttribute("allSupervisors", academicStaffServices.retrieveAllAcademicStaffMembers());
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
			Thesis updateThesis = thesisServices.retrieveThesisById(id);
			updateThesis.setDeleted(true);
			thesisRepo.save(updateThesis);
			model.addAttribute("allThesis", thesisServices.retrieveActiveTheses());
			return "redirect:/thesis/showAll";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/changeSupervisor/{idThesis}/{idSupervisor}")
	public String changeSupervisorByThesisAndSupervisorIdGetFunc(@PathVariable("idThesis") Long id,
			@PathVariable("idSupervisor") Long idSupervisor, Model model) {
		try {
			model.addAttribute("allThesis", thesisServices.changeSupervisorByThesisAndSupervisorId(id, idSupervisor));
			return "redirect:/thesis/showAll";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/addReviewerByThesisId/{idThesis}/{idReviewer}")
	public String addReviewerByThesisId(@PathVariable("idThesis") Long id, @PathVariable("idReviewer") Long idReviewer,
			Model model) {
		try {
			model.addAttribute("allThesis", thesisServices.addReviewerByThesisId(id, idReviewer));
			return "redirect:/thesis/showAll";
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
	public String updateThesisStatusPostFunc(@PathVariable("id") Long id, @Valid Thesis thesis, BindingResult result) {
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
	
	 @GetMapping("/thesis/export")
	    public void exportThesesToExcel(HttpServletResponse response) throws IOException {
	        List<Thesis> theses = thesisServices.retrieveActiveTheses();

	        Workbook workbook = new XSSFWorkbook();

	        Sheet sheet = workbook.createSheet("Theses");

	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("Title (LV)");
	        headerRow.createCell(1).setCellValue("Title (EN)");
	        headerRow.createCell(2).setCellValue("Aim");
	        headerRow.createCell(3).setCellValue("Tasks");
	        headerRow.createCell(4).setCellValue("Student");
	        headerRow.createCell(5).setCellValue("Supervisor");

	        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        int rowNum = 1;
	        for (Thesis thesis : theses) {
	            Row dataRow = sheet.createRow(rowNum++);
	            dataRow.createCell(0).setCellValue(thesis.getTitleLv());
	            dataRow.createCell(1).setCellValue(thesis.getTitleEn());
	            dataRow.createCell(2).setCellValue(thesis.getAim());
	            dataRow.createCell(3).setCellValue(thesis.getTasks());
	            dataRow.createCell(4).setCellValue(thesis.getStudent().getName() + " " + thesis.getStudent().getSurname());
	            dataRow.createCell(5).setCellValue(thesis.getSupervisor().getName() + " " + thesis.getSupervisor().getSurname());
	        }

	        sheet.setColumnWidth(0, 8000);
	        sheet.setColumnWidth(1, 8000);
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 8000);
	        sheet.setColumnWidth(5, 8000);

	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=theses.xlsx");

	        workbook.write(response.getOutputStream());
	        workbook.close();
	    }

}
