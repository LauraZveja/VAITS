package lv.vaits.services.users.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.services.users.IStudentServices;

public class StudentServicesImplementation implements IStudentServices {

	@Autowired
	private IStudentRepo studentRepo;

	@Override
	public Student createNewStudent(String name, String surname, String personcode, User user, String matriculaNo,
			boolean financialDebt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student retrieveStudentById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Student> retrieveAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudentById(int id, String name, String surname, String personcode, User user,
			String matriculaNo, boolean financialDebt) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudentById(int id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Course> retrieveAllEnrolledCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enrollInCourse(Course course) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Thesis retrieveStudentThesisByStudentId(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Thesis submitThesis(String titleLv, String titleEn, String aim, String tasks, Student student,
			AcademicStaff supervisor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comment> retrieveAllCommentsByThesisId() {
		// TODO Auto-generated method stub
		return null;
	}

}
