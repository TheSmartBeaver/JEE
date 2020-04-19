package myapp.dao;

import java.util.Collection;
import java.util.List;

import myapp.model.Person;
import myapp.model.Group;

public interface IPersonDao {

	   // récupérer les personnes
	   List<Person> findAllPersons(long groupId);

	   // lire une personne
	   Person findPerson(long id);

	   // modification ou ajout d'une nouvelle personne
	   void savePerson(Person p);

	   }