package lv.vaits.models;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtherApplicationsTest {

    AcademicStaff supervisor = new AcademicStaff();
    Student student = new Student();
    Thesis testThesis = new Thesis("Title LV", "Title EN", "Aim", "Tasks", student, supervisor);

    OtherApplications otherApplications = new OtherApplications();

    OtherApplications goodApplication = new OtherApplications(ApplicationType.CHANGE_THESIS_TOPIC, "Activity", testThesis);

    Thesis thesis = new Thesis();

    @Test
    void getIdoa() {
        assertEquals(0, otherApplications.getIdoa());
    }

    @Test
    void getApplicationType() {
        assertEquals(ApplicationType.CHANGE_THESIS_TOPIC, goodApplication.getApplicationType());
    }

    @Test
    void getActivity() {
        assertEquals("Activity", goodApplication.getActivity());
    }

    @Test
    void getThesis() {
        assertEquals(testThesis, goodApplication.getThesis());
    }

    @Test
    void setApplicationType() {
        otherApplications.setApplicationType(ApplicationType.OTHER);
        assertEquals(ApplicationType.OTHER, otherApplications.getApplicationType());
    }

    @Test
    void setActivity() {
        otherApplications.setActivity("Activity");
        assertEquals("Activity", otherApplications.getActivity());
    }

    @Test
    void setThesis() {
        otherApplications.setThesis(thesis);
        assertEquals(thesis, otherApplications.getThesis());
    }


}