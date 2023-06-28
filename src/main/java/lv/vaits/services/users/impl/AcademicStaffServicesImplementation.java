package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.repos.users.IAcademicStaffRepo;
import lv.vaits.services.users.IAcademicStaffServices;

@Service
public class AcademicStaffServicesImplementation implements IAcademicStaffServices {

	@Autowired
	IAcademicStaffRepo academicStaffRepo;

	@Override
	public ArrayList<AcademicStaff> retrieveAllAcademicStaff() {
		return (ArrayList<AcademicStaff>) academicStaffRepo.findAll();
	}

}
