package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.StudyProgram;

public interface IStudyProgramRepo extends CrudRepository<StudyProgram, Long> {

}
