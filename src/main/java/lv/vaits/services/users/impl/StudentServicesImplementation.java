package lv.vaits.services.users.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Comment;
import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
import lv.vaits.repos.ICommentRepo;
import lv.vaits.repos.ICourseRepo;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.services.users.IStudentServices;

@Service
public class StudentServicesImplementation implements IStudentServices {

	@Autowired
	private IStudentRepo studentRepo;

	@Autowired
	private ICourseRepo courseRepo;

	@Autowired
	private IThesisRepo thesisRepo;

	@Autowired
	private ICommentRepo commentsRepo;

	@Override
	public Student createNewStudent(String name, String surname, String personcode, User user, String matriculaNo,
			boolean financialDebt) {
		return studentRepo.save(new Student(name, surname, personcode, user, matriculaNo, financialDebt));
	}

	@Override
	public Student retrieveStudentById(Long id) throws Exception {
		if (studentRepo.existsById(id)) {
			return studentRepo.findById(id).get();
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public ArrayList<Student> retrieveAllStudents() {
		return (ArrayList<Student>) studentRepo.findAll();
	}

	@Override
	public Student updateStudentById(Long id, String name, String surname, String personcode, User user,
			String matriculaNo, boolean financialDebt) throws Exception {
		if (studentRepo.existsById(id)) {
			Student updatedStudent = studentRepo.findById(id).get();
			updatedStudent.setName(name);
			updatedStudent.setSurname(surname);
			updatedStudent.setPersoncode(personcode);
			updatedStudent.setUser(user);
			updatedStudent.setMatriculaNo(matriculaNo);
			updatedStudent.setFinancialDebt(financialDebt);
			return studentRepo.save(updatedStudent);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteStudentById(Long id) throws Exception {
		if (studentRepo.existsById(id)) {
			studentRepo.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}

	}

	@Override
	public ArrayList<Course> retrieveAllDebtCoursesByStudentId(Long id) throws Exception {
		if (studentRepo.existsById(id)) {
			return courseRepo.findByDebtStudentsIdp(id);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void addDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws Exception {
		if (studentRepo.existsById(idStudent)) {
			Student student = studentRepo.findById(idStudent).get();
			for (Long courseId : debtCourses) {
				if (!courseRepo.findById(courseId).get().getDebtStudents().contains(student)) {
					courseRepo.findById(courseId).get().addStudent(student);
					courseRepo.save(courseRepo.findById(courseId).get());
				}
				if (!student.getDebtCourse().contains(courseRepo.findById(courseId).get())) {
					student.addDebtCourse(courseRepo.findById(courseId).get());
					studentRepo.save(student);
				}
			}
		} else {
			throw new Exception("Wrong Student id");
		}
	}

	@Override
	public void removeDebtCourseByStudentIdAndCourseId(Long idStudent, Long idCourse) throws Exception {
		if (studentRepo.existsById(idStudent)) {
			if (courseRepo.existsById(idCourse)) {
				Course course = courseRepo.findById(idCourse).get();
				Student student = studentRepo.findById(idStudent).get();
				if (course.getDebtStudents().contains(student)) {
					course.getDebtStudents().remove(student);
				} else {
					System.out.println("Course does not have this Student as a debtor!");
				}
				if (student.getDebtCourse().contains(course)) {
					student.getDebtCourse().remove(course);
				} else {
					System.out.println("Student has no debt in this Course!");
				}
			} else {
				throw new Exception("Wrong Course id");
			}
		} else {
			throw new Exception("Wrong Student id");
		}
	}

	@Override
	public ArrayList<Thesis> retrieveStudentThesisByStudentId(Long id) throws Exception {
		if (studentRepo.existsById(id)) {
			return thesisRepo.findByStudentIdp(id);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public Thesis submitThesisByStudentId(String titleLv, String titleEn, String aim, String tasks, Long idStudent,
			AcademicStaff supervisor) throws Exception {
		if (studentRepo.existsById(idStudent)) {
			return thesisRepo
					.save(new Thesis(titleLv, titleEn, aim, tasks, studentRepo.findById(idStudent).get(), supervisor));
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public ArrayList<Comment> retrieveAllCommentsByThesisId(Long id) throws Exception {
		if (thesisRepo.existsById(id)) {
			return commentsRepo.findByThesisIdt(id);
		} else {
			throw new Exception("Wrong id");
		}
	}

}
