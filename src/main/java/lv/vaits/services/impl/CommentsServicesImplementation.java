package lv.vaits.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
import lv.vaits.models.users.AcademicStaff;
import lv.vaits.repos.ICommentRepo;
import lv.vaits.repos.IThesisRepo;
import lv.vaits.services.ICommentsServices;

@Service
public class CommentsServicesImplementation implements ICommentsServices {

	@Autowired
	private ICommentRepo commentRepo;

	@Autowired
	private IThesisRepo thesisRepo;

	@Override
	public void createNewComment(String description, AcademicStaff staff, Thesis thesis) {
		Comment newComment = commentRepo.save(new Comment(description, staff, thesis));
		Thesis temp = newComment.getThesis();
		thesis.addCommentToThesis(newComment);
		thesisRepo.save(temp);
	}

	@Override
	public Comment updateCommentById(Long id, String description, AcademicStaff staff, Thesis thesis) throws Exception {
		if (commentRepo.existsById(id)) {
			Comment updateComment = commentRepo.findById(id).get();
			updateComment.setDescription(description);
			updateComment.setStaff(staff);
			updateComment.setThesis(thesis);
			return commentRepo.save(updateComment);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public void deleteCommentById(Long id) throws Exception {
		if (commentRepo.existsById(id)) {
			commentRepo.deleteById(id);
		} else {
			throw new Exception("Wrong id");
		}
	}

	@Override
	public ArrayList<Comment> selectAllByThesisId(Long id) {
		return commentRepo.findByThesisIdt(id);
	}

	@Override
	public ArrayList<Comment> retrieveAllComments() {
		return (ArrayList<Comment>) commentRepo.findAll();
	}

	@Override
	public Comment retrieveCommentById(Long id) throws Exception {
		if (commentRepo.existsById(id)) {
			return commentRepo.findById(id).get();
		} else {
			throw new Exception("Wrong id");
		}
	}

}
