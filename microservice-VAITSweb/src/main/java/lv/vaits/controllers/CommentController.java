package lv.vaits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.vaits.models.Comment;
import lv.vaits.models.Thesis;
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
		model.addAttribute("allComments", commentServices.retrieveAllComments());
		return "comment-add-page";
	}

	@PostMapping("/comment/addNew")
	public String insertCommentPostFunc(@Valid Comment comment, @RequestParam("thesis") Thesis thesis,
			BindingResult result) {
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

	@GetMapping("/comment/update/{id}")
	public String updateCommentByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("comment", commentServices.retrieveCommentById(id));
			model.addAttribute("allThesis", thesisServices.retrieveAllThesis());
			model.addAttribute("allStaff", academicStaffServices.retrieveAllAcademicStaffMembers());
			return "comment-update-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@PostMapping("/comment/update/{id}")
	public String updateCommentByIdPostFunc(@PathVariable("id") Long id, @Valid Comment comment, BindingResult result) {
		if (!result.hasErrors()) {
			try {
				Comment updatedComment = commentServices.updateCommentById(id, comment.getDescription(),
						comment.getStaff(), comment.getThesis());
				return "redirect:/comment/showAll/" + updatedComment.getIdco();
			} catch (Exception e) {
				return "redirect:/comment/error";
			}
		} else {
			return "comment-update-page";
		}
	}

	@GetMapping("/comment/showAll/{id}")
	public String oneCommentByIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("comment", commentServices.retrieveCommentById(id));
			return "comment-one-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/comment/remove/{id}")
	public String deleteCommentById(@PathVariable("id") Long id, Model model) {
		try {
			commentServices.deleteCommentById(id);
			model.addAttribute("allComments", commentServices.retrieveAllComments());
			return "comment-all-page";
		} catch (Exception e) {
			return "error-page";
		}
	}

	@GetMapping("/thesis/showAllComments/{id}")
	public String allCommentsByThesisIdGetFunc(@PathVariable("id") Long id, Model model) {
		try {
			model.addAttribute("thesis", thesisServices.retrieveThesisById(id));
			model.addAttribute("allComments", commentServices.selectAllByThesisId(id));
			return "thesis-comments-page";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

}
