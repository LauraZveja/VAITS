package lv.vaits.user.repos.users;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.user.models.users.User;

public interface IUserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

	User findByUsername(String username);

	User findByIdu(long idu);
}
