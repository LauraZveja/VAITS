package lv.vaits.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.users.Person;

public interface IPersonRepo extends CrudRepository<Person, Long>{

}
