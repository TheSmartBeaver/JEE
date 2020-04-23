package mybootapp.dao;

import mybootapp.model.Party;
import mybootapp.model.Person;

public interface IPersonDao {
	
	public void savePerson(Person p);
	public void saveParty(Party p);
	public Iterable<Party> findAllParties();
	public Iterable<Person> findAllPersons();
	public Iterable<Person> findAllPersonsinParty(Long partyId);
	public Person findByEmail(String email);
	Person findPersonById(Long id);
	Party findPartyById(Long id);
	Party findPartyByPartyName(String partyName);
}
