package lv.vaits.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.Student;

public interface IStudentRepo extends CrudRepository<Student, Long> {

	boolean existsByMatriculaNo(String matriculaNo);

	Student findByMatriculaNo(String matriculaNo);


}
