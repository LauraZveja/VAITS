package lv.vaits.services.users.impl;

import java.util.ArrayList;

import lv.vaits.utils.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Degree;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.services.users.IAcademicStaffServices;

@Service
public class AcademicStaffServicesImplementation implements IAcademicStaffServices {

	@Autowired
	private IAcademicStaffRepo academicStaffRepo;

	@Autowired
	private IThesisRepo thesisRepo;

	@Override
	public AcademicStaff createNewAcademicStaffMember(String name, String surname, String personcode, Long id_user,
			Degree degree) {
		return academicStaffRepo.save(new AcademicStaff(name, surname, personcode, id_user, degree));
	}

	@Override
	public AcademicStaff updateAcademicStaffMemberById(Long id, String name, String surname, String personcode,
			Long id_user, Degree degree) throws MyException {
		if (academicStaffRepo.existsById(id)) {
			AcademicStaff updatedAcademicStaffMember = academicStaffRepo.findById(id).get();
			updatedAcademicStaffMember.setName(name);
			updatedAcademicStaffMember.setSurname(surname);
			updatedAcademicStaffMember.setPersoncode(personcode);
			updatedAcademicStaffMember.setId_user(id_user);
			updatedAcademicStaffMember.setDegree(degree);
			return academicStaffRepo.save(updatedAcademicStaffMember);
		} else {
			throw new MyException("ID not found");

		}
	}

	@Override
	public void deleteAcademicStaffMemberById(Long id) throws MyException {
		if (academicStaffRepo.existsById(id)) {
			if (academicStaffRepo.findById(id).get().getThesis().isEmpty()) {
				if (academicStaffRepo.findById(id).get().getThesisForReviews().isEmpty()) {
					academicStaffRepo.deleteById(id);
				} else {
					throw new MyException("ID assigned to a thesis as a reviewer ID");
				}
			} else {
				throw new MyException("ID assigned to a thesis as a supervisor ID");
			}
		} else {
			throw new MyException("ID not found");
		}
	}

	@Override
	public AcademicStaff retrieveAcademicStaffMemberById(Long id) throws MyException {
		if (academicStaffRepo.existsById(id)) {
			return academicStaffRepo.findById(id).get();
		} else {
			throw new MyException("ID not found");
		}
	}

	@Override
	public ArrayList<AcademicStaff> retrieveAllAcademicStaffMembers() {
		return (ArrayList<AcademicStaff>) academicStaffRepo.findAll();
	}

}
