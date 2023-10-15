package lv.vaits.services.users;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface IStudentServices {

	Student createNewStudent(String name, String surname, String personcode, Long id_user, String matriculaNo,
			boolean financialDebt);

	Student retrieveStudentById(Long id) throws Exception;

	ArrayList<Student> retrieveAllStudents();

	Student updateStudentById(Long id, String name, String surname, String personcode, Long id_user, String matriculaNo,
			boolean financialDebt) throws Exception;

	void deleteStudentById(Long id) throws Exception;

	ArrayList<Course> retrieveAllDebtCoursesByStudentId(Long id) throws Exception;

	void addDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws Exception;

	void removeDebtCourseByStudentId(Long idStudent, List<Long> debtCourses) throws Exception;

	ArrayList<Thesis> retrieveStudentThesisByStudentId(Long id) throws Exception;

	Thesis submitThesisByStudentId(String titleLv, String titleEn, String aim, String tasks, Long idStudent,
			AcademicStaff supervisor) throws Exception;

	Workbook exportStudentsToExcel();

	Student retrieveStudentByMatriculaNo(String matriculaNo) throws Exception;

	void importStudentsFromExcel(InputStream excelFile) throws IOException;

	XWPFDocument exportStudentsToWord();

	void importStudentsFromWord(InputStream docxFile) throws IOException;

}
