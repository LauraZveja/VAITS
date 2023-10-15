package lv.vaits.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Course;

public interface ICourseRepo extends CrudRepository<Course, Long> {

	ArrayList<Course> findByDebtStudentsIdp(Long id);

	//Course createNewCourse();

}
