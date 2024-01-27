package lv.vaits.controllers;

import lv.vaits.services.ICommentsServices;
import lv.vaits.services.IThesisServices;
import lv.vaits.services.users.IAcademicStaffServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommentsServices commentServices;

    @MockBean
    private IThesisServices thesisServices;

    @MockBean
    private IAcademicStaffServices academicStaffServices;

    @Test
    void insertCommentGetFunc() {
    }

    @Test
    void insertCommentPostFunc() {
    }

    @Test
    void updateCommentByIdGetFunc() {
    }

    @Test
    void updateCommentByIdPostFunc() {
    }

    @Test
    void oneCommentByIdGetFunc() {
    }

    @Test
    void deleteCommentById() {
    }

    @Test
    void allCommentsByThesisIdGetFunc() {
    }

    @Test
    void getAllComments() {
    }

    @Test
    void addNewComment() {
    }
}