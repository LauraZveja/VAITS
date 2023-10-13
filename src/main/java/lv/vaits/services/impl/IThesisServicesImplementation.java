package lv.vaits.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.AcceptanceStatus;
import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.repos.ICommentRepo;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.services.IThesisServices;

@Service
public class IThesisServicesImplementation implements IThesisServices {

	@Autowired
	private ICommentRepo commentRepo;

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
			Thesis updateThesis = thesisRepo.findById(idThesis).get();
			updateThesis.addReviewer(academicStaffRepo.findById(idReviewer).get());
			return thesisRepo.save(updateThesis);
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
	public ArrayList<Thesis> retrieveActiveTheses() {
		return thesisRepo.findByIsDeletedFalse();
	}

}
