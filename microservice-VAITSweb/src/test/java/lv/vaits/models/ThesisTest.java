package lv.vaits.models;

import lv.vaits.models.users.AcademicStaff;
import lv.vaits.models.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class ThesisTest {

    Thesis thesis = new Thesis();
    AcademicStaff supervisor = new AcademicStaff(); // Mock or create actual instance
    Student student = new Student(); // Mock or create actual instance
    Thesis testThesis = new Thesis("Title LV", "Title EN", "Aim", "Tasks", student, supervisor);

    AcademicStaff staff = new AcademicStaff();

    Comment goodComment = new Comment("Good comment", staff, thesis);

    private Collection<OtherApplications> otherApplications = new ArrayList<>();
    private Collection<Comment> comments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        thesis.setReviewers(new ArrayList<>()); // Initialize reviewers
        thesis.setComments(new ArrayList<>()); // Initialize comments
        thesis.setThesisApplications(new ArrayList<>()); // Initialize thesisApplications
        thesis.setMessages(new ArrayList<>()); // Initialize messages

    }


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

    @Test
    void testAddAndRemoveReviewer() {
        AcademicStaff reviewer = new AcademicStaff();
        thesis.addReviewer(reviewer);
        assertTrue(thesis.getReviewers().contains(reviewer), "Reviewer should be added initially");
        thesis.addReviewer(reviewer);
        assertEquals(1, thesis.getReviewers().size(), "Reviewer should not be added again");
        thesis.removeReviewer(reviewer);
        assertFalse(thesis.getReviewers().contains(reviewer), "Reviewer should be removed");
    }

    @Test
    void testAddCommentToThesis() {
        Comment comment = new Comment();
        thesis.addCommentToThesis(comment);
        assertTrue(thesis.getComments().contains(comment), "Comment should be added initially");
        thesis.addCommentToThesis(comment);
        assertEquals(1, thesis.getComments().size(), "Comment should not be added again");
    }

    @Test
    void testAddThesisApplicationToThesis() {
        ThesisApplications thesisApplication = new ThesisApplications();
        thesis.addThesisApplicationToThesis(thesisApplication);
        assertTrue(thesis.getThesisApplications().contains(thesisApplication), "Thesis application should be added initially");
        thesis.addThesisApplicationToThesis(thesisApplication);
        assertEquals(1, thesis.getThesisApplications().size(), "Thesis application should not be added again");
    }

    @Test
    void testAddMessageToThesis() {
        Message message = new Message();
        thesis.addMessageToThesis(message);
        assertTrue(thesis.getMessages().contains(message), "Message should be added initially");
        thesis.addMessageToThesis(message);
        assertEquals(1, thesis.getMessages().size(), "Message should not be added again");
    }

    @Test
    void testStatusFromSupervisor() {
        thesis.setStatusFromSupervisor(true);
        assertTrue(thesis.isStatusFromSupervisor(), "Status from supervisor should be true");
    }

    @Test
    void testIsDeleted() {
        thesis.setDeleted(true);
        assertTrue(thesis.isDeleted(), "isDeleted should be true");
    }

    @Test
    void testSetAccDateTime() {
        LocalDateTime now = LocalDateTime.now();
        thesis.setAccDateTime(now);
        assertEquals(now, thesis.getAccDateTime(), "accDateTime should be set correctly");
    }

    @Test
    void testSetAccStatus() {
        thesis.setAccStatus(AcceptanceStatus.ACCEPTED);
        assertEquals(AcceptanceStatus.ACCEPTED, thesis.getAccStatus(), "accStatus should be set correctly");
    }

}