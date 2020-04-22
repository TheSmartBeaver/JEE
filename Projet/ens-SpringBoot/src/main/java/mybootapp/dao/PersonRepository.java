package mybootapp.dao;

import java.util.List;

import mybootapp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);
	List<Person> findByFirstNameLike(String firstName);
	List<Person> findByMail(String mail);
	List<Person> findByMailLike(String mail);

}