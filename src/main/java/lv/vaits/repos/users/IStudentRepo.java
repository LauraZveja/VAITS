package lv.vaits.repos.users;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.Student;

public interface IStudentRepo extends CrudRepository<Student, Long> {

	//ArrayList<Student> findByStudentIdp(Long id);

}
