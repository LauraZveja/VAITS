package lv.vaits.services.impl;

import java.util.ArrayList;

import lv.vaits.dto.CommentDTO;
import lv.vaits.repos.users.IAcademicStaffRepo;
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

	@Autowired
	private IAcademicStaffRepo staffRepo;

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

	@Override
	public ArrayList<CommentDTO> retrieveAllDataForComments() {
		ArrayList<CommentDTO> result = new ArrayList<>();
		ArrayList<Comment> allComments = (ArrayList<Comment>) commentRepo.findAll();

		for (Comment temp : allComments){
			result.add(new CommentDTO(temp.getDescription(),
					temp.getStaff().getName(), temp.getStaff().getSurname(),
					temp.getThesis().getTitleLv()));
		}
		return result;
	}

	@Override
	public void insertCommentByCommentDTO(CommentDTO commentDTO) throws Exception {
		ArrayList<AcademicStaff> staffMembers = staffRepo.findByNameAndSurname(commentDTO.getStaffName(), commentDTO.getStaffSurname());
		Thesis thesis = thesisRepo.findByTitleLv(commentDTO.getThesisTitle());

		if (staffMembers != null && thesis != null){
			Comment comment = new Comment(commentDTO.getDescription(), staffMembers.get(0) ,thesis);
			commentRepo.save(comment);
		} else {
			throw new Exception("Thesis or academic staff by such name and surname does not exist.");
		}
	}

}
