package lv.vaits.models;

import lv.vaits.models.users.AcademicStaff;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    // declaring objects

    AcademicStaff staff = new AcademicStaff();
    Thesis thesis = new Thesis();
    LocalDateTime commentDate = LocalDateTime.now();

    Comment goodComment = new Comment("Good comment", staff, thesis);

    @Test
    void testConstructor() {
        assertEquals("Good comment", goodComment.getDescription());
        assertEquals(staff, goodComment.getStaff());
        assertEquals(thesis, goodComment.getThesis());
        assertEquals(commentDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));

    }


    @Test
    void getIdco() {
        assertEquals(0, goodComment.getIdco());
    }

    @Test
    void getDescription() {
        assertEquals("Good comment", goodComment.getDescription());
    }

    @Test
    void getCommentDate() {
        assertEquals(commentDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void getStaff() {
        assertEquals(staff, goodComment.getStaff());
    }

    @Test
    void getThesis() {
        assertEquals(thesis, goodComment.getThesis());
    }

    @Test
    void setDescription() {
        goodComment.setDescription("Test description");
        assertEquals("Test description", goodComment.getDescription());
    }

    @Test
    void setCommentDate() {
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        goodComment.setCommentDate(newDate);
        assertEquals(newDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void setStaff() {
        AcademicStaff newStaff = new AcademicStaff();
        goodComment.setStaff(newStaff);
        assertEquals(newStaff, goodComment.getStaff());
    }

    @Test
    void setThesis() {
        Thesis newThesis = new Thesis();
        goodComment.setThesis(newThesis);
        assertEquals(newThesis, goodComment.getThesis());
    }
}