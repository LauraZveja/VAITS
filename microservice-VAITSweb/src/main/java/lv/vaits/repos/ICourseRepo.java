package lv.vaits.repos;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lv.vaits.models.Course;
import lv.vaits.models.users.Student;

public interface ICourseRepo extends CrudRepository<Course, Long> {

	ArrayList<Course> findByDebtStudentsIdp(Long id);

	@Query("SELECT s FROM Course c JOIN c.debtStudents s WHERE c.idc = :courseId")
	Collection<Student> findAllDebtorsByCourseId(@Param("courseId") Long courseId);

}
