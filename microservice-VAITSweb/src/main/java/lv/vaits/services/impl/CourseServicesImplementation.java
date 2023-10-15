package lv.vaits.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Course;
import lv.vaits.models.users.Student;
import lv.vaits.repos.ICourseRepo;
import lv.vaits.repos.users.IStudentRepo;
import lv.vaits.services.ICourseServices;

@Service
public class CourseServicesImplementation implements ICourseServices {

	@Autowired
	private ICourseRepo courseRepo;

	@Autowired
	private IStudentRepo studentRepo;

	@Override
	public ArrayList<Course> selectAllCourse() {
		return (ArrayList<Course>) courseRepo.findAll();
	}

	@Override
	public Course retrieveCourseById(Long id) throws Exception {
		if (courseRepo.existsById(id)) {
			return courseRepo.findById(id).get();
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public Course createNewCourse(String title, int creditPoints) {
		return courseRepo.save(new Course(title, creditPoints));
	}

	@Override
	public Course updateCourseById(Long id, String title, int creditPoints) throws Exception {
		if (courseRepo.existsById(id)) {
			Course updatedCourse = courseRepo.findById(id).get();
			updatedCourse.setTitle(title);
			updatedCourse.setCreditPoints(creditPoints);
			return courseRepo.save(updatedCourse);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteCourseById(Long id) throws Exception {
		if (courseRepo.existsById(id)) {
			courseRepo.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void addStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws Exception {
		if (courseRepo.existsById(idCourse)) {
			Course course = courseRepo.findById(idCourse).get();
			for (Long studentId : debtStudents) {
				Student debtor = studentRepo.findById(studentId).get();
				if (!course.getDebtStudents().contains(debtor)) {
					course.addStudent(debtor);
					courseRepo.save(course);
				}
				if (!debtor.getDebtCourse().contains(course)) {
					debtor.addDebtCourse(course);
					studentRepo.save(debtor);
				}
			}
		} else {
			throw new Exception("Wrong Student id");
		}
	}

	@Override
	public void removeStudentDebtByCourseId(Long idCourse, List<Long> debtStudents) throws Exception {
		if (courseRepo.existsById(idCourse)) {
			Course course = courseRepo.findById(idCourse).get();
			for (Long studentId : debtStudents) {
				Student debtor = studentRepo.findById(studentId).get();
				if (course.getDebtStudents().contains(debtor)) {
					course.getDebtStudents().remove(debtor);
					courseRepo.save(course);
				}
				if (debtor.getDebtCourse().contains(course)) {
					debtor.getDebtCourse().remove(course);
					studentRepo.save(debtor);
				}
			}
		} else {
			throw new Exception("Wrong Student id");
		}
	}

	@Override
	public Collection<Student> retrieveAllStudentDebtsByCourseId(Long id) throws Exception {
		if (courseRepo.existsById(id)) {
			return courseRepo.findById(id).get().getDebtStudents();
		} else {
			throw new Exception("Wrong id");
		}
	}

}
