package lv.vaits.services;

import java.io.IOException;
import java.util.ArrayList;

import lv.vaits.models.AcceptanceStatus;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.utils.MyException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface IThesisServices {

	Thesis createNewThesis(String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor);

	Thesis updateThesisById(Long id, String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor) throws MyException;

	void deleteThesisById(Long id) throws MyException;

	Thesis retrieveThesisById(Long id) throws MyException;

	ArrayList<Thesis> retrieveAllThesis();

	ArrayList<Thesis> retrieveActiveTheses();

	Thesis changeSupervisorByThesisAndSupervisorId(Long idThesis, Long idAcademicStaff) throws MyException;

	Thesis addReviewerByThesisId(Long idThesis, Long idReviewer) throws MyException;

	Thesis updateThesisStatus(Long idThesis, AcceptanceStatus status) throws MyException;

	Workbook exportThesisToExcel();

	XWPFDocument exportThesisToWord();

	Thesis deleteThesisReviewerById(Long idThesis, Long idReviewer) throws MyException;

}
