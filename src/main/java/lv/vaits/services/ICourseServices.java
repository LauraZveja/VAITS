package lv.vaits.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lv.vaits.models.Course;
import lv.vaits.models.users.Student;

public interface ICourseServices {

	Course retrieveCourseById(Long id) throws Exception;

	ArrayList<Course> selectAllCourse();

	Course createNewCourse(String title, int creditPoints);

	Course updateCourseById(Long id, String title, int creditPoints) throws Exception;

	void deleteCourseById(Long id) throws Exception;

	void addStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws Exception;

	void removeStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws Exception;

	Collection<Student> retrieveAllStudentDebtsByCourseId(Long id) throws Exception;
}
