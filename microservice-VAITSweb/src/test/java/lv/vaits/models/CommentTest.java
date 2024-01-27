package lv.vaits.models;

import lv.vaits.models.users.AcademicStaff;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

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


}