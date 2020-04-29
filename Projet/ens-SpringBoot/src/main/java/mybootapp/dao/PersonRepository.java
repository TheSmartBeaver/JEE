package mybootapp.dao;

import java.util.List;

import mybootapp.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);
	List<Person> findByFirstNameLikeIgnoreCase(String firstName);
	List<Person> findByLastName(String lastName);
	List<Person> findByLastNameLikeIgnoreCase(String lastName);
	List<Person> findByMail(String mail);
	List<Person> findByMailLike(String mail);

}