package lv.vaits.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Comment;

public interface ICommentRepo extends CrudRepository<Comment, Long> {

	ArrayList<Comment> findByThesisIdt(Long id);

}
