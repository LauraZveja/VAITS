package lv.vaits.services.users;

import java.util.ArrayList;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.utils.MyException;

public interface IAcademicStaffServices {

	AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, Long idUser,
			Degree degree);

	AcademicStaff updateAcademicStaffMemberById(Long id, String name, String surname, String personcode, Long idUser,
			Degree degree) throws MyException;

	void deleteAcademicStaffMemberById(Long id) throws MyException;

	AcademicStaff retrieveAcademicStaffMemberById(Long id) throws MyException;

	ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers();

}
