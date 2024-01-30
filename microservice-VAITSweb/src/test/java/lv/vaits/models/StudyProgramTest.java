package lv.vaits.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

class StudyProgramTest {

    StudyProgram goodStudyProgram = new StudyProgram("Study program", Faculty.ITF, LevelOfStudy.FIRST_LEVEL);
    StudyProgram studyProgram = new StudyProgram();

    CalendarSchedule goodCalendarSchedule = new CalendarSchedule(2024, "Defense", LocalDate.now(), goodStudyProgram);

    @Test
    void addCalendarSchedule() {
        goodStudyProgram.addCalendarSchedule(goodCalendarSchedule);
        assertTrue(goodStudyProgram.getCalendarSchedule().contains(goodCalendarSchedule));
    }

    @Test
    void setCourseTitleLv() {
        studyProgram.setCourseTitleLv("Course title LV");
        assertEquals("Course title LV", studyProgram.getCourseTitleLv());
    }

    @Test
    void setFaculty() {
        studyProgram.setFaculty(Faculty.ITF);
        assertEquals(Faculty.ITF, studyProgram.getFaculty());
    }

    @Test
    void setLevelOfStudy() {
        studyProgram.setLevelOfStudy(LevelOfStudy.FIRST_LEVEL);
        assertEquals(LevelOfStudy.FIRST_LEVEL, studyProgram.getLevelOfStudy());
    }

    @Test
    void setStudentStudyProgram() {
        StudentStudyProgram studentStudyProgram = new StudentStudyProgram();
        studentStudyProgram.setStudyProgram(studyProgram);
        assertEquals(studyProgram, studentStudyProgram.getStudyProgram());
        Collection<StudentStudyProgram> studentStudyPrograms = new ArrayList<>();
        studentStudyPrograms.add(studentStudyProgram);
        studyProgram.setStudentStudyProgram(studentStudyPrograms);
        assertTrue(studyProgram.getStudentStudyProgram().contains(studentStudyProgram));


    }

    @Test
    void setCalendarSchedule() {
        Collection<CalendarSchedule> calendarSchedules = new ArrayList<>();
        calendarSchedules.add(goodCalendarSchedule);
        studyProgram.setCalendarSchedule(calendarSchedules);
        assertEquals(calendarSchedules, studyProgram.getCalendarSchedule());
    }

    @Test
    void getIdsp() {
        assertEquals(0, goodStudyProgram.getIdsp());
    }

    @Test
    public void getCourseTitleLv() {
        assertEquals("Study program", goodStudyProgram.getCourseTitleLv());
    }

    @Test
    public void getFaculty() {
        assertEquals(Faculty.ITF, goodStudyProgram.getFaculty());
    }

    @Test
    public void getLevelOfStudy() {
        assertEquals(LevelOfStudy.FIRST_LEVEL, goodStudyProgram.getLevelOfStudy());
    }

    @Test
    public void getStudentStudyProgram() {
        StudentStudyProgram studentStudyProgram = new StudentStudyProgram();
        studentStudyProgram.setStudyProgram(studyProgram);
        assertEquals(studyProgram, studentStudyProgram.getStudyProgram());

    }

    @Test
    public void getCalendarSchedule() {
        goodStudyProgram.addCalendarSchedule(goodCalendarSchedule);
        assertTrue(goodStudyProgram.getCalendarSchedule().contains(goodCalendarSchedule));

    }

    @Test
    void addDuplicateCalendarSchedule() {
        CalendarSchedule calendarSchedule = new CalendarSchedule(2024, "Defense", LocalDate.now(), goodStudyProgram);
        goodStudyProgram.addCalendarSchedule(calendarSchedule);
        goodStudyProgram.addCalendarSchedule(calendarSchedule);
        Collection<CalendarSchedule> retrievedCalendarSchedules = goodStudyProgram.getCalendarSchedule();
        assertEquals(1, retrievedCalendarSchedules.size());
    }

}