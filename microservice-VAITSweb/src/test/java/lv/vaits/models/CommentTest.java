package lv.vaits.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lv.vaits.models.users.AcademicStaff;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CommentTest {
    AcademicStaff staff = new AcademicStaff();
    Thesis thesis = new Thesis();
    LocalDateTime commentDate = LocalDateTime.now();

    Comment goodComment = new Comment("Good comment", staff, thesis);

    @Test
    public void testConstructor() {
        assertEquals("Good comment", goodComment.getDescription());
        assertEquals(staff, goodComment.getStaff());
        assertEquals(thesis, goodComment.getThesis());
        assertEquals(commentDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));

    }


    @Test
    public void getIdco() {
        assertEquals(0, goodComment.getIdco());
    }

    @Test
    public void getDescription() {
        assertEquals("Good comment", goodComment.getDescription());
    }

    @Test
    public void getCommentDate() {
        assertEquals(commentDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void getStaff() {
        assertEquals(staff, goodComment.getStaff());
    }

    @Test
    public void getThesis() {
        assertEquals(thesis, goodComment.getThesis());
    }

    @Test
    public void setDescription() {
        goodComment.setDescription("Test description");
        assertEquals("Test description", goodComment.getDescription());
    }

    @Test
    public void setCommentDate() {
        LocalDateTime newDate = LocalDateTime.now().plusDays(1);
        goodComment.setCommentDate(newDate);
        assertEquals(newDate.truncatedTo(ChronoUnit.SECONDS), goodComment.getCommentDate().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    public void setStaff() {
        AcademicStaff newStaff = new AcademicStaff();
        goodComment.setStaff(newStaff);
        assertEquals(newStaff, goodComment.getStaff());
    }

    @Test
    public void setThesis() {
        Thesis newThesis = new Thesis();
        goodComment.setThesis(newThesis);
        assertEquals(newThesis, goodComment.getThesis());
    }

}