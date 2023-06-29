package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lv.vaits.models.Comment;
import lv.vaits.services.ICommentsServices;
import lv.vaits.services.IThesisServices;
import lv.vaits.services.users.IAcademicStaffServices;

@Controller
public class CommentController {

	@Autowired
	private ICommentsServices commentServices;

	@Autowired
	private IThesisServices thesisServices;

	@Autowired
	private IAcademicStaffServices academicStaffServices;

	@GetMapping("/comment/addNew")
	public String insertCommentGetFunc(Comment comment, Model model) {
		model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
		model.addAttribute("allStaff", academicStaffServices.retrieveAllAcademicStaffMembers());
		return "comment-add-page";
	}

	@PostMapping("/comment/addNew")
	public String insertCommentPostFunc(@Valid Comment comment, BindingResult result) {
		if (!result.hasErrors()) {
			commentServices.createNewComment(comment.getDescription(), comment.getStaff(), comment.getThesis());
			return "redirect:/comment/showAll";
		} else {
			return "comment-add-page";
		}
	}
	
	@GetMapping("/comment/showAll")
	public String allThesisGetFunc(Model model) {
	    model.addAttribute("allComments", commentServices.retrieveAllComments());
	    return "comment-all-page";
	}

}
