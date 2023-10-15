package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Message;

public interface IMessageRepo extends CrudRepository<Message, Long>{

}
