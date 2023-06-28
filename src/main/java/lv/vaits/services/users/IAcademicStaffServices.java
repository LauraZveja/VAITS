package lv.vaits.services.users;

import java.util.ArrayList;

import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;

public interface IAcademicStaffServices {
	
	AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, User user);
	
	AcademicStaff updateAcademicStaffMemberById(int id, String name, String surname, String personcode, User user);
	
	void deleteAcademicStaffMemberById (int id) throws Exception;
	
	AcademicStaff retrieveAcademicStaffMemberById (int id) throws Exception;
	
	ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers();
	
	Thesis assignThesis(String titleLv, String titleEn, String aim, String tasks, Student student);
	
	Thesis getThesisByAcademicStaffMember(AcademicStaff supervisor, String titleLv, String titleEn, String aim, String tasks, Student student);
	
	Thesis addThesisForReview(String titleLv, String titleEn, String aim, String tasks, Student student, AcademicStaff supervisor );
	
	void removeThesisFromReview(int id) throws Exception;
	
	ArrayList<Thesis> retrieveAllTheses();
}