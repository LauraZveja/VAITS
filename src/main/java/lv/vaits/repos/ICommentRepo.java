package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Comment;

public interface ICommentRepo extends CrudRepository<Comment, Long>{

}
