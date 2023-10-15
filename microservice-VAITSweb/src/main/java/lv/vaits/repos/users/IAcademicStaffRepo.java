package lv.vaits.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.AcademicStaff;

public interface IAcademicStaffRepo extends CrudRepository<AcademicStaff, Long>{

}
