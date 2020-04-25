package mybootapp.dao;

import java.util.List;

import mybootapp.model.Party;
import mybootapp.model.Person;

public interface IPersonDao {
	
	public void savePerson(Person p);
	public void saveParty(Party p);
	public Iterable<Party> findAllParties();
	public Iterable<Person> findAllPersons();
	public Iterable<Person> findAllPersonsinParty(Long partyId);
	public Person findByEmail(String email);
	public Person findPersonById(Long id);
	public Party findPartyById(Long id);
	public Party findPartyByPartyName(String partyName);
	public Iterable<Party> findPartyByPartyNameLike(String partyName);
	
	public Iterable<Person> findPersonByFirstNameLike(String firstName);
	public Iterable<Person> findPersonByLastNameLike(String firstName);
}
