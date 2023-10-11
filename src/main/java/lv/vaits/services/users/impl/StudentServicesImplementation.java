package lv.vaits.services.users.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lv.vaits.repos.users.IUserRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Course;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import lv.vaits.models.users.User;
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
    private IUserRepo userRepo;

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

    @Override
    public Workbook exportStudentsToExcel() {
        List<Student> students = retrieveAllStudents();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Students");

        Row headRow = sheet.createRow(0);

        String[] headers = {"Name", "Surname", "Personcode", "User e-mail", "Matricula number", "Student financial debt"};
        int rowNum = 1;

        for (int i = 0; i < headers.length; i++) {
            headRow.createCell(i).setCellValue(headers[i]);
        }

        for (Student student : students) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(student.getName());
            dataRow.createCell(1).setCellValue(student.getSurname());
            dataRow.createCell(2).setCellValue(student.getPersoncode());
            dataRow.createCell(3).setCellValue(student.getUser().getEmail());
            dataRow.createCell(4).setCellValue(student.getMatriculaNo());
            dataRow.createCell(5).setCellValue(student.isFinancialDebt());
        }

        for (int i = 0; i < 6; i++) {
            sheet.setColumnWidth(i, 8000);
        }

        return workbook;
    }

    @Override
    public Student retrieveStudentByMatriculaNo(String matriculaNo) throws Exception {
        if (studentRepo.existsByMatriculaNo(matriculaNo)){
            return studentRepo.findByMatriculaNo(matriculaNo);
        } else {
            throw new Exception("Wrong Matricula No");
        }
    }

    @Override
    public void importStudentsFromExcel(InputStream excelFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        int currentUserIndex = 5;

        List<User> users = (List<User>) userRepo.findAll();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Skip the header row
            if (row.getRowNum() == 0) {
                continue;
            }

            String name = row.getCell(0).getStringCellValue();
            String surname = row.getCell(1).getStringCellValue();
            String personcode = row.getCell(2).getStringCellValue();
            String matriculaNo = row.getCell(4).getStringCellValue();
            boolean financialDebt = row.getCell(5).getBooleanCellValue();

            if (!studentRepo.existsByMatriculaNo(matriculaNo)) {
                User currentUser = users.get(currentUserIndex);
                createNewStudent(name, surname, personcode, currentUser, matriculaNo, financialDebt);
                currentUserIndex++;


            }
        }
    }

}
