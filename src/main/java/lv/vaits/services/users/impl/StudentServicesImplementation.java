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
				Course debtCourse = courseRepo.findById(courseId).get();
				if (!debtCourse.getDebtStudents().contains(student)) {
					debtCourse.addStudent(student);
					courseRepo.save(debtCourse);
				}
				if (!student.getDebtCourse().contains(debtCourse)) {
					student.addDebtCourse(debtCourse);
					studentRepo.save(student);
				}
			}
		} else {
			throw new Exception("Wrong Student id");
		}
	}

	@Override
	public void removeDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws Exception {
		if (studentRepo.existsById(idStudent)) {
			Student student = studentRepo.findById(idStudent).get();
			for (Long courseId : debtCourses) {
				Course debtCourse = courseRepo.findById(courseId).get();
				if (debtCourse.getDebtStudents().contains(student)) {
					debtCourse.getDebtStudents().remove(student);
					courseRepo.save(debtCourse);
				}
				if (student.getDebtCourse().contains(debtCourse)) {
					student.getDebtCourse().remove(debtCourse);
					studentRepo.save(student);
				}
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

}
