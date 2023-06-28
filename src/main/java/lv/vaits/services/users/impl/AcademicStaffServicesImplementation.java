package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.services.users.IAcademicStaffServices;

public class AcademicStaffServicesImplementation implements IAcademicStaffServices{
	
	@Autowired
	private IAcademicStaffRepo academicStaffRepo;

	@Override
	public AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AcademicStaff updateAcademicStaffMemberById(int id, String name, String surname, String personcode,
			User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAcademicStaffMemberById(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AcademicStaff retrieveAcademicStaffMemberById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Thesis assignThesis(String titleLv, String titleEn, String aim, String tasks, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Thesis getThesisByAcademicStaffMember(AcademicStaff supervisor, String titleLv, String titleEn, String aim,
			String tasks, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Thesis addThesisForReview(String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeThesisFromReview(int id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Thesis> retrieveAllTheses() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
