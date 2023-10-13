package lv.vaits.services;

import java.util.ArrayList;

import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;

public interface ICommentsServices {

	void createNewComment(String description, AcademicStaff staff, Thesis thesis);

	Comment updateCommentById(Long id, String description, AcademicStaff staff, Thesis thesis) throws Exception;

	void deleteCommentById(Long id) throws Exception;

	ArrayList<Comment> selectAllByThesisId(Long id);
	
	ArrayList<Comment> retrieveAllComments();

	Comment retrieveCommentById(Long id) throws Exception;

}
