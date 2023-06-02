package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Course;

public interface ICourseRepo extends CrudRepository<Course, Long>{

}
