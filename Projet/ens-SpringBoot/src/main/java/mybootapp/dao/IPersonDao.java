package mybootapp.dao;

import mybootapp.model.Party;
import mybootapp.model.Person;

public interface IPersonDao {
	
	public void savePerson(Person p);
	public void saveParty(Party p);
	public Iterable<Party> findAllParties();
	public Iterable<Person> findAllPersons();
	public Person findByEmail(String email);
	Person findById(Long id);

}
