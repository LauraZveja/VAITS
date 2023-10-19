package lv.vaits.services;

import java.util.ArrayList;

import lv.vaits.dto.CommentDTO;
import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.utils.MyException;

public interface ICommentsServices {

	void createNewComment(String description, AcademicStaff staff, Thesis thesis);

	Comment updateCommentById(Long id, String description, AcademicStaff staff, Thesis thesis) throws MyException;

	void deleteCommentById(Long id) throws MyException;

	ArrayList<Comment> selectAllByThesisId(Long id);
	
	ArrayList<Comment> retrieveAllComments();

	Comment retrieveCommentById(Long id) throws MyException;

	ArrayList<CommentDTO> retrieveAllDataForComments();

	void insertCommentByCommentDTO(CommentDTO commentDTO) throws MyException;


}
