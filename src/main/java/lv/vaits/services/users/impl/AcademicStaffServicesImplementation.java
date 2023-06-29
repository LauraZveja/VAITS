package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.services.users.IAcademicStaffServices;

@Service
public class AcademicStaffServicesImplementation implements IAcademicStaffServices {

	@Autowired
	private IAcademicStaffRepo academicStaffRepo;

	@Override
	public AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, User user,
			Degree degree) {
		return academicStaffRepo.save(new AcademicStaff(name, surname, personcode, user, degree));
	}

	@Override
	public AcademicStaff updateAcademicStaffMemberById(Long id, String name, String surname, String personcode,
			User user, Degree degree) throws Exception {
		if (academicStaffRepo.existsById(id)) {
			AcademicStaff updatedAcademicStaffMember = academicStaffRepo.findById(id).get();
			updatedAcademicStaffMember.setName(name);
			updatedAcademicStaffMember.setSurname(surname);
			updatedAcademicStaffMember.setPersoncode(personcode);
			updatedAcademicStaffMember.setUser(user);
			updatedAcademicStaffMember.setDegree(degree);
			return academicStaffRepo.save(updatedAcademicStaffMember);
		} else {
			throw new Exception("ID not found");

		}
	}

	@Override
	public void deleteAcademicStaffMemberById(Long id) throws Exception {
		if (academicStaffRepo.existsById(id)) {
			academicStaffRepo.deleteById(id);
		} else {
			throw new Exception("ID not found");
		}
	}

	@Override
	public AcademicStaff retrieveAcademicStaffMemberById(Long id) throws Exception {
		if (academicStaffRepo.existsById(id)) {
			return academicStaffRepo.findById(id).get();
		} else {
			throw new Exception("ID not found");
		}
	}

	@Override
	public ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers() {
		return (ArrayList<AcademicStaff>) academicStaffRepo.findAll();
	}

}
