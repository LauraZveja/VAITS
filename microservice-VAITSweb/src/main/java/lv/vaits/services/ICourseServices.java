package lv.vaits.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lv.vaits.models.Course;
import lv.vaits.models.users.Student;
import lv.vaits.utils.MyException;

public interface ICourseServices {

	Course retrieveCourseById(Long id) throws MyException;

	ArrayList<Course> selectAllCourse();

	Course createNewCourse(String title, int creditPoints);

	Course updateCourseById(Long id, String title, int creditPoints) throws MyException;

	void deleteCourseById(Long id) throws MyException;

	void addStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws MyException;

	void removeStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws MyException;

	Collection<Student> retrieveAllStudentDebtsByCourseId(Long id) throws MyException;
}
