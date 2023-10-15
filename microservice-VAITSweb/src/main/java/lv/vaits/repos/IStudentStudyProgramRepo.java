package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.StudentStudyProgram;

public interface IStudentStudyProgramRepo extends CrudRepository<StudentStudyProgram, Long> {

}
