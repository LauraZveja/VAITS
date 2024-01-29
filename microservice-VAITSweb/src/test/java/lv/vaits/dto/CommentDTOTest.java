package lv.vaits.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentDTOTest {

    CommentDTO goodCommentDTO = new CommentDTO("Good comment", "John", "Doe", "Thesis title");
    CommentDTO badStaffName = new CommentDTO("Good comment", "john", "Doe", "Thesis title");
    CommentDTO badStaffSurname = new CommentDTO("Good comment", "John", "doe", "Thesis title");
    CommentDTO badThesisTitle = new CommentDTO("Good comment", "John", "Doe", "thesis title");
    CommentDTO badDescription = new CommentDTO("Go", "John", "Doe", "Thesis title");

    CommentDTO badThesisLength = new CommentDTO("Good comment", "John", "Doe", "Thesis title that is too long");

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    public void testGoodComment() {
        assertEquals("Good comment", goodCommentDTO.getDescription());
        assertEquals("John", goodCommentDTO.getStaffName());
        assertEquals("Doe", goodCommentDTO.getStaffSurname());
        assertEquals("Thesis title", goodCommentDTO.getThesisTitle());
    }

    @Test
    public void testBadStaffName() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badStaffName);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("staffName", violation.getPropertyPath().toString());
    }

    @Test
    public void testBadStaffSurname() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badStaffSurname);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("staffSurname", violation.getPropertyPath().toString());
    }

    @Test
    public void testBadThesisTitle() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badThesisTitle);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Pirmajam burtam jabut lielajam!", violation.getMessage());
        assertEquals("thesisTitle", violation.getPropertyPath().toString());
    }

    @Test
    public void testBadDescription() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badDescription);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Garumam jabut vismaz 3 rakstzimju garam!", violation.getMessage());
        assertEquals("description", violation.getPropertyPath().toString());
    }

    @Test
    public void testBadThesisLength() {
        Set<ConstraintViolation<CommentDTO>> violations = validator.validate(badThesisLength);
        assertEquals(1, violations.size());
        ConstraintViolation<CommentDTO> violation = violations.iterator().next();
        assertEquals("Garumam jabut no 3 lidz 25 rakstzimju garam!", violation.getMessage());
        assertEquals("thesisTitle", violation.getPropertyPath().toString());
    }

}