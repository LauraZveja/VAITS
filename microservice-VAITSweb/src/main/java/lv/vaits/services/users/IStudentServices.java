package lv.vaits.services.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.utils.MyException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface IStudentServices {

	Student createNewStudent(String name, String surname, String personcode, Long id_user, String matriculaNo,
			boolean financialDebt);

	Student retrieveStudentById(Long id) throws MyException;

	ArrayList<Student> retrieveAllStudents();

	Student updateStudentById(Long id, String name, String surname, String personcode, Long id_user, String matriculaNo,
			boolean financialDebt) throws MyException;

	void deleteStudentById(Long id) throws MyException;

	ArrayList<Course> retrieveAllDebtCoursesByStudentId(Long id) throws MyException;

	void addDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws MyException;

	void removeDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws MyException;

	ArrayList<Thesis> retrieveStudentThesisByStudentId(Long id) throws MyException;

	Thesis submitThesisByStudentId(String titleLv, String titleEn, String aim, String tasks, Long idStudent,
			AcademicStaff supervisor) throws MyException;

	Workbook exportStudentsToExcel();

	Student retrieveStudentByMatriculaNo(String matriculaNo) throws MyException;

	void importStudentsFromExcel(InputStream excelFile) throws IOException;

	XWPFDocument exportStudentsToWord();

	void importStudentsFromWord(InputStream docxFile) throws IOException;

}
