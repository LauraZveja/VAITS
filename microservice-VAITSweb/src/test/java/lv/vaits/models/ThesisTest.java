package lv.vaits.models;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThesisTest {

    Thesis thesis = new Thesis();
    AcademicStaff supervisor = new AcademicStaff(); // Mock or create actual instance
    Student student = new Student(); // Mock or create actual instance
    Thesis testThesis = new Thesis("Title LV", "Title EN", "Aim", "Tasks", student, supervisor);


    @Test
    void getIdt() {
        assertEquals(0, thesis.getIdt());
    }

    @Test
    void getTitleLv() {
        assertEquals("Title LV", testThesis.getTitleLv());
    }

    @Test
    void getTitleEn() {
        assertEquals("Title EN", testThesis.getTitleEn());
    }

    @Test
    void getAim() {
        assertEquals("Aim", testThesis.getAim());
    }

    @Test
    void getTasks() {
        assertEquals("Tasks", testThesis.getTasks());
    }

    @Test
    void getSubmitDateTime() {
        assertNotNull(testThesis.getSubmitDateTime());
    }

   @Test
    void setTitleLv() {
        testThesis.setTitleLv("New Title LV");
        assertEquals("New Title LV", testThesis.getTitleLv());
    }

    @Test
    void setTitleEn() {
        testThesis.setTitleEn("New Title EN");
        assertEquals("New Title EN", testThesis.getTitleEn());
    }

    @Test
    void setAim() {
        testThesis.setAim("New Aim");
        assertEquals("New Aim", testThesis.getAim());
    }

    @Test
    void setTasks() {
        testThesis.setTasks("New Tasks");
        assertEquals("New Tasks", testThesis.getTasks());
    }

    @Test
    void getStudent() {
        assertEquals(student, testThesis.getStudent());
    }

    @Test
    void setStudent() {
        Student newStudent = new Student();
        testThesis.setStudent(newStudent);
        assertEquals(newStudent, testThesis.getStudent());
    }

    @Test
    void getSupervisor() {
        assertEquals(supervisor, testThesis.getSupervisor());
    }

    @Test
    void setSupervisor() {
        AcademicStaff newSupervisor = new AcademicStaff();
        testThesis.setSupervisor(newSupervisor);
        assertEquals(newSupervisor, testThesis.getSupervisor());
    }

    @Test
    void getAccStatus() {
        assertEquals(AcceptanceStatus.SUBMITTED, testThesis.getAccStatus());
    }


}