package mybootapp.dao;

import java.util.List;

import mybootapp.model.Party;
import mybootapp.model.Person;

public interface IPersonDao {
	
	/**
	 * 
	 * @param p l'objet Person que l'on veut sauvegarder
	 */
	public void savePerson(Person p);
	/**
	 * 
	 * @param p l'objet Groupe que l'on veut sauvegarder
	 */
	public void saveParty(Party p);
	/**
	 * 
	 * @return tous les groupes présents
	 */
	public Iterable<Party> findAllParties();
	/**
	 * 
	 * @return toutes les personnes présentes
	 */
	public Iterable<Person> findAllPersons();
	/**
	 * 
	 * @param partyId
	 * @return toutes les personnes appartenant au groupe dont l'id correspont à l'id passé en param
	 */
	public Iterable<Person> findAllPersonsinParty(Long partyId);
	/**
	 * 
	 * @param email
	 * @return la personne possédant le mail passé en param
	 */
	public Person findByEmail(String email);
	/**
	 * 
	 * @param id
	 * @return la personne dont l'id correspont à l'id passé en param
	 */
	public Person findPersonById(Long id);
	/**
	 * 
	 * @param id
	 * @return le groupe dont l'id correspont à l'id passé en param
	 */
	public Party findPartyById(Long id);
	/**
	 * 
	 * @param partyName
	 * @return le groupe dont le nom est le même que celui passé en param
	 */
	public Party findPartyByPartyName(String partyName);
	/**
	 * 
	 * @param partyName
	 * @return  les groupe dont le nom est composé du param
	 */
	public Iterable<Party> findPartyByPartyNameLike(String partyName);
	/**
	 * 
	 * @param firstName
	 * @return les personnes dont le FirstName est composé du param
	 */
	public Iterable<Person> findPersonByFirstNameLike(String firstName);
	/**
	 * 
	 * @param firstName
	 * @return les personnes dont le LastName est composé du param
	 */
	public Iterable<Person> findPersonByLastNameLike(String firstName);
}
