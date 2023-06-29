package lv.vaits.services.users;

import java.util.ArrayList;


import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;

public interface IAcademicStaffServices {
	
	AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, User user, Degree degree);
	
	AcademicStaff updateAcademicStaffMemberById(Long id, String name, String surname, String personcode, User user, Degree degree) throws Exception;
	
	void deleteAcademicStaffMemberById (Long id) throws Exception;
	
	AcademicStaff retrieveAcademicStaffMemberById (Long id) throws Exception;
	
	ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers();
	
}

