package lv.vaits.user.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.user.models.users.Authorities;

public interface IAuthorityRepo extends CrudRepository<Authorities, Long> {

}
