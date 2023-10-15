package lv.vaits.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vaits.models.ThesisApplications;

public interface IThesisApplications extends CrudRepository<ThesisApplications, Long> {

}
