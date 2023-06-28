package lv.vaits.services;

import java.util.ArrayList;

import lv.vaits.models.AcceptanceStatus;
import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;

public interface IThesisServices {
	
	Thesis createNewThesis(String titleLv, String titleEn, String aim, String tasks, Student student, AcademicStaff supervisor);
	
	Thesis updateThesisById(Long id, String titleLv, String titleEn, String aim, String tasks, Student student, AcademicStaff supervisor);
	
	void deleteThesisById(Long id) throws Exception;
	
	Student retrieveThesisById(Long id) throws Exception;
	
	ArrayList<Thesis> retrieveAllThesis();
	
	Thesis changeSupervisorByThesisAndSupervisorId(Long idThesis, Long idAcademicStaff) throws Exception;
	
	void addReviewerByThesisId(Long idThesis, Long idReviewer);
	
    Thesis updateThesisStatus(Long idThesis, AcceptanceStatus status) throws Exception;
    
    Thesis addCommentToThesis(Long idThesis, Comment comment) throws Exception;

}
