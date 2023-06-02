package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.Thesis;

public interface IThesisRepo extends CrudRepository<Thesis, Long>{
	
	

}
