package lv.vaits.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.User;

public interface IUserRepo extends CrudRepository<User, Long>{

}
