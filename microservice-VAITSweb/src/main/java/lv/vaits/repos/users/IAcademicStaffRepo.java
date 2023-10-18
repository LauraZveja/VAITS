package lv.vaits.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.AcademicStaff;

import java.util.ArrayList;

public interface IAcademicStaffRepo extends CrudRepository<AcademicStaff, Long>{

    ArrayList<AcademicStaff> findByNameAndSurname(String staffName, String staffSurname);
}
