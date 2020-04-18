package mybootapp.dao;

import java.util.List;

import mybootapp.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);

}