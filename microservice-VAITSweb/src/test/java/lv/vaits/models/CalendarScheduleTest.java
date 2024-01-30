package lv.vaits.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarScheduleTest {

    StudyProgram goodStudyProgram = new StudyProgram("Study program", Faculty.ITF, LevelOfStudy.FIRST_LEVEL);

    CalendarSchedule goodCalendarSchedule = new CalendarSchedule(2024, "Defense", LocalDate.now(), goodStudyProgram);

    StudyProgram studyProgram = new StudyProgram();

    CalendarSchedule calendarSchedule = new CalendarSchedule();

    @Test
    void getIdcs() {
        assertEquals(0, goodCalendarSchedule.getIdcs());
    }

    @Test
    void getStudyYear() {
        assertEquals(2024, goodCalendarSchedule.getStudyYear());

    }

    @Test
    void setStudyYear() {
        calendarSchedule.setStudyYear(2025);
        assertEquals(2025, calendarSchedule.getStudyYear());
    }

    @Test
    void getActivity() {
        assertEquals("Defense", goodCalendarSchedule.getActivity());
    }

    @Test
    void setActivity() {
        calendarSchedule.setActivity("Graduation");
        assertEquals("Graduation", calendarSchedule.getActivity());
    }

    @Test
    void getDeadline() {
        assertEquals(LocalDate.now(), goodCalendarSchedule.getDeadline());

    }

    @Test
    void setDeadline() {
        calendarSchedule.setDeadline(LocalDate.now().plusDays(1));
        assertEquals(LocalDate.now().plusDays(1), calendarSchedule.getDeadline());

    }

    @Test
    void getStudyProgram() {
        assertEquals(goodStudyProgram, goodCalendarSchedule.getStudyProgram());

    }

    @Test
    void setStudyProgram() {
        calendarSchedule.setStudyProgram(studyProgram);
        assertEquals(studyProgram, calendarSchedule.getStudyProgram());

    }

}