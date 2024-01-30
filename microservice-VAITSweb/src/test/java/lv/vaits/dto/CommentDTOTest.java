package lv.vaits.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommentDTOTest {

    CommentDTO goodCommentDTO = new CommentDTO("Good comment", "John", "Doe", "Thesis title");
    CommentDTO badStaffName = new CommentDTO("Good comment", "john", "Doe", "Thesis title");
    CommentDTO badStaffSurname = new CommentDTO("Good comment", "John", "doe", "Thesis title");
    CommentDTO badThesisTitle = new CommentDTO("Good comment", "John", "Doe", "thesis title");
    CommentDTO badDescription = new CommentDTO("Go", "John", "Doe", "Thesis title");

    CommentDTO commentDTO = new CommentDTO();

    CommentDTO badThesisLength = new CommentDTO("Good comment", "John", "Doe", "Thesis title that is too long");

    CommentDTO goodConstructorDTO = new CommentDTO("Good comment", "John", "Doe", "Thesis title");

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void testGoodComment() {
        assertEquals("Good comment", goodCommentDTO.getDescription());
        assertEquals("John", goodCommentDTO.getStaffName());
        assertEquals("Doe", goodCommentDTO.getStaffSurname());
        assertEquals("Thesis title", goodCommentDTO.getThesisTitle());
    }

    @Test
    void testBadStaffName() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badStaffName);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("staffName", violation.getPropertyPath().toString());
    }

    @Test
    void testBadStaffSurname() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badStaffSurname);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("staffSurname", violation.getPropertyPath().toString());
    }

    @Test
    void testBadThesisTitle() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badThesisTitle);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("thesisTitle", violation.getPropertyPath().toString());
    }

    @Test
    void testBadDescription() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badDescription);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Garumam jabut vismaz 3 rakstzimju garam!", violation.getMessage());
        assertEquals("description", violation.getPropertyPath().toString());
    }

    @Test
    void testBadThesisLength() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badThesisLength);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Garumam jabut no 3 lidz 25 rakstzimju garam!", violation.getMessage());
        assertEquals("thesisTitle", violation.getPropertyPath().toString());
    }

    @Test
    void testNoArgsConstructor() {
        assertNotNull(commentDTO, "No-args constructor should create a non-null object.");
    }

    @Test
    void testSetters() {

        commentDTO.setDescription("New Description");
        assertEquals("New Description", commentDTO.getDescription(), "Setter for description failed.");

        commentDTO.setStaffName("Alice");
        assertEquals("Alice", commentDTO.getStaffName(), "Setter for staffName failed.");

        commentDTO.setStaffSurname("Smith");
        assertEquals("Smith", commentDTO.getStaffSurname(), "Setter for staffSurname failed.");

        commentDTO.setThesisTitle("New Thesis Title");
        assertEquals("New Thesis Title", commentDTO.getThesisTitle(), "Setter for thesisTitle failed.");
    }

    @Test
    void testToString() {
        String expectedString = "CommentDTO(description=Good comment, staffName=John, staffSurname=Doe, thesisTitle=Thesis title)";
        assertEquals(expectedString, goodConstructorDTO.toString(), "ToString method does not match expected format.");
    }






}