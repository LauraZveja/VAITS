package lv.vaits.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.AcceptanceStatus;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.services.IThesisServices;

@Service
public class IThesisServicesImplementation implements IThesisServices {

	@Autowired
	private IAcademicStaffRepo academicStaffRepo;

	@Autowired
	private IThesisRepo thesisRepo;

	@Autowired
	private IStudentRepo studentRepo;

	@Override
	public Thesis createNewThesis(String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor) {
		return thesisRepo.save(new Thesis(titleLv, titleEn, aim, tasks, student, supervisor));
	}

	@Override
	public Thesis updateThesisById(Long id, String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor) throws Exception {
		if (thesisRepo.existsById(id) && studentRepo.existsById(student.getIdp())) {
			Thesis updateThesis = thesisRepo.findById(id).get();
			updateThesis.setTitleLv(titleLv);
			updateThesis.setTitleEn(titleEn);
			updateThesis.setAim(aim);
			updateThesis.setTasks(tasks);
			updateThesis.setStudent(student);
			updateThesis.setSupervisor(supervisor);
			return thesisRepo.save(updateThesis);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteThesisById(Long id) throws Exception {
		if (thesisRepo.existsById(id)) {
			thesisRepo.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}

	}

	@Override
	public Thesis retrieveThesisById(Long id) throws Exception {
		if (thesisRepo.existsById(id)) {
			return thesisRepo.findById(id).get();
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public ArrayList<Thesis> retrieveAllThesis() {
		return (ArrayList<Thesis>) thesisRepo.findAll();
	}

	@Override
	public Thesis changeSupervisorByThesisAndSupervisorId(Long idThesis, Long idAcademicStaff) throws Exception {
		if (thesisRepo.existsById(idThesis) && academicStaffRepo.existsById(idAcademicStaff)) {
			Thesis updateThesis = thesisRepo.findById(idThesis).get();
			updateThesis.setSupervisor(academicStaffRepo.findById(idAcademicStaff).get());
			return thesisRepo.save(updateThesis);
		} else {
			throw new Exception("Wrong thesis and / or supervisor id");
		}

	}

	@Override
	public Thesis addReviewerByThesisId(Long idThesis, Long idReviewer) throws Exception {
		if (thesisRepo.existsById(idThesis) && academicStaffRepo.existsById(idReviewer)) {
			if (!thesisRepo.findById(idThesis).get().getReviewers()
					.contains(academicStaffRepo.findById(idReviewer).get())) {
				Thesis updateThesis = thesisRepo.findById(idThesis).get();
				updateThesis.addReviewer(academicStaffRepo.findById(idReviewer).get());
				return thesisRepo.save(updateThesis);
			}
			throw new Exception("Supervisor can't also be reviewer!");

		} else {
			throw new Exception("Wrong thesis and / or reviewer id");
		}

	}

	@Override
	public Thesis updateThesisStatus(Long idThesis, AcceptanceStatus status) throws Exception {
		if (thesisRepo.existsById(idThesis) && status != null) {
			Thesis updateThesis = thesisRepo.findById(idThesis).get();
			updateThesis.setAccStatus(status);
			return thesisRepo.save(updateThesis);
		} else {
			throw new Exception("Wrong id and / or invalid Acceptance status");
		}
	}

	@Override
	public Workbook exportThesisToExcel() {
		List<Thesis> theses = retrieveActiveTheses();

		// Tiek izveidota jauna Excel darba grāmata
		Workbook workbook = new XSSFWorkbook();

		// Tiek izveidota jauna Excel darba lapa ar nosaukumu Theses
		Sheet sheet = workbook.createSheet("Theses");

		// Tiek izveidoti rindu nosaukumi
		String[] headers = { "Title (LV)", "Title (EN)", "Aim", "Tasks", "Student", "Supervisor" };

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			headerRow.createCell(i).setCellValue(headers[i]);
		}

		// Tiek inicializēts rindas numurs
		int rowNum = 1;

		// Tiek aizpildīts Excel fails ar datiem par tēzēm
		for (Thesis thesis : theses) {

			Row dataRow = sheet.createRow(rowNum++);
			dataRow.createCell(0).setCellValue(thesis.getTitleLv());
			dataRow.createCell(1).setCellValue(thesis.getTitleEn());
			dataRow.createCell(2).setCellValue(thesis.getAim());
			dataRow.createCell(3).setCellValue(thesis.getTasks());
			dataRow.createCell(4).setCellValue(thesis.getStudent().getName() + " " + thesis.getStudent().getSurname());
			dataRow.createCell(5)
					.setCellValue(thesis.getSupervisor().getName() + " " + thesis.getSupervisor().getSurname());
		}

		// Tiek uzstādīts kolonnu platums
		for (int i = 0; i < 6; i++) {
			sheet.setColumnWidth(i, 8000);
		}
		return workbook;
	}

	@Override
	public XWPFDocument exportThesisToWord() {
		List<Thesis> theses = retrieveActiveTheses();

		XWPFDocument document = new XWPFDocument();

		// Create a paragraph with the document title
		XWPFParagraph titleParagraph = document.createParagraph();
		XWPFRun titleRun = titleParagraph.createRun();
		titleRun.setText("Theses");

		// Create a table to display the data
		XWPFTable table = document.createTable(theses.size() + 1, 6); // Rows: theses.size() + 1, Columns: 6

		// Set column names
		XWPFTableRow headerRow = table.getRow(0);
		String[] headers = { "Title (LV)", "Title (EN)", "Aim", "Tasks", "Student", "Supervisor" };

		for (int i = 0; i < headers.length; i++) {
			headerRow.getCell(i).setText(headers[i]);
		}

		// Fill the table with data
		for (int i = 0; i < theses.size(); i++) {
			Thesis thesis = theses.get(i);
			XWPFTableRow dataRow = table.getRow(i + 1);
			dataRow.getCell(0).setText(thesis.getTitleLv());
			dataRow.getCell(1).setText(thesis.getTitleEn());
			dataRow.getCell(2).setText(thesis.getAim());
			dataRow.getCell(3).setText(thesis.getTasks());
			dataRow.getCell(4).setText(thesis.getStudent().getName() + " " + thesis.getStudent().getSurname());
			dataRow.getCell(5).setText(thesis.getSupervisor().getName() + " " + thesis.getSupervisor().getSurname());
		}

		return document;
	}

	@Override
	public ArrayList<Thesis> retrieveActiveTheses() {
		return thesisRepo.findByIsDeletedFalse();
	}

}
