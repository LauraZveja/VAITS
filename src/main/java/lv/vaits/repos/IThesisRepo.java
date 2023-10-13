package lv.vaits.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Thesis;

public interface IThesisRepo extends CrudRepository<Thesis, Long> {

	ArrayList<Thesis> findByStudentIdp(Long id);

	ArrayList<Thesis> findByIsDeletedFalse();

}
