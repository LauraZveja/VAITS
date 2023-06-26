package lv.vaits.services.users;

import java.util.ArrayList;

import jakarta.validation.constraints.NotNull;
import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;

public interface IStudentServices {

	Student createNewStudent(String name, String surname, String personcode, User user, String matriculaNo,
			boolean financialDebt);

	Student retrieveStudentById(int id) throws Exception;

	ArrayList<Student> retrieveAllStudents();

	Student updateStudentById(int id, String name, String surname, String personcode, User user, String matriculaNo,
			boolean financialDebt) throws Exception;

	void deleteStudentById(int id) throws Exception;

	ArrayList<Course> retrieveAllEnrolledCourses();

	void enrollInCourse(Course course) throws Exception;

	Thesis retrieveStudentThesisByStudentId(int id) throws Exception;

	Thesis submitThesis(String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor);

	ArrayList<Comment> retrieveAllCommentsByThesisId();

}
