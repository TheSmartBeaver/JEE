import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;

import mybootapp.Starter;
import mybootapp.dao.PersonService;
import mybootapp.dao.PersonRepository;
import mybootapp.model.Party;
import mybootapp.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class PersonServiceTest {

	@Autowired
	private PersonRepository repoPerson;
	@Autowired
	private PersonService personService;

	Party group1;
	Party group2;
	Person person1;
	Person person2;
	ArrayList<Party> formedGroups;
	ArrayList<Person> formedPersons;

	@Before
	public void init() {
		personService.deleteAll();
		formedGroups = new ArrayList<>();
		formedPersons = new ArrayList<>();

		group1 = new Party("party1");
		group2 = new Party("party2");
		formedGroups.add(group1);
		formedGroups.add(group2);

		/// CREATION D'UNE PREMIERE PERSONNE

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(gc.YEAR, 2020);
		gc.set(gc.DAY_OF_YEAR, 55);

		person1 = new Person("TAPIS", "Bernard", "TAPISBernard@gmail.com", "TAPIS");
		person1.setWebsite("TAPISBernard.exemple.fr");
		person1.setBirthDay(gc.getTime());
		person1.setPersonParty(group1);
		formedPersons.add(person1);

		/// CREATION D'UNE DEUXIEME PERSONNE
		gc.set(gc.YEAR, 2010);
		gc.set(gc.DAY_OF_YEAR, 25);

		person2 = new Person("ZIDANE", "Zinedine", "ZIDANEZinedine@gmail.com", "ZIDANE");
		person2.setWebsite("ZIDANEZinedine.exemple.fr");
		person2.setBirthDay(gc.getTime());
		person2.setPersonParty(group2);
		formedPersons.add(person2);

		// SAVE

		for (Party part : formedGroups)
			personService.saveParty(part);
		for (Person pers : formedPersons) {
			personService.savePerson(pers);
		}
	}

	@Test
	  public void testmail() {
		assertEquals(person2.toString(),personService.findByEmail("ZIDANEZinedine@gmail.com").toString());
	  }
	
	@Test
	  public void testFindPersonByFirstName() {
		System.err.println(personService.findAllPersons());
		ArrayList<Person> findPersonByFirstNameLikeTest= new ArrayList<Person>();
		Iterable<Person> testfindPersonByFirstNameLike = personService.findPersonByFirstNameLike("ZIDANE") ;
		
		for (Person pa : testfindPersonByFirstNameLike) {
			findPersonByFirstNameLikeTest.add(pa);
		}
		
		assertTrue(findPersonByFirstNameLikeTest.size()==1);
		assertEquals(person2.toString(),findPersonByFirstNameLikeTest.get(0).toString());
		
	  }
	
	@Test
	  public void testPersonsinParty() {
			
		ArrayList<Person> personsinParty= new ArrayList<Person>();
		Iterable<Person> testAllPersonsInParty = personService.findAllPersonsinParty(personService.findPartyByPartyName("party2").getId()) ;
		
		for (Person pa : testAllPersonsInParty) {
			personsinParty.add(pa);
		}
		
		assertTrue(personsinParty.size()==1);
		assertEquals(person2.toString(),personsinParty.get(0).toString());
	  }
}