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
import mybootapp.dao.DAOPerson;
import mybootapp.dao.PersonRepository;
import mybootapp.model.Party;
import mybootapp.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class DaoPersonnTest {

	@Autowired
	private PersonRepository repoPerson;
	@Autowired
	private DAOPerson dao;

	Party test;
	Party teeest;
	Person p;
	Person pp;
	ArrayList<Party> formedGroups;
	ArrayList<Person> formedPersons;

	@Before
	public void init() {
		dao.deleteAll();
		formedGroups = new ArrayList<>();
		formedPersons = new ArrayList<>();

		test = new Party("party1");
		teeest = new Party("party2");
		formedGroups.add(test);
		formedGroups.add(teeest);

		/// CREATION D'UNE PREMIERE PERSONNE

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(gc.YEAR, 2020);
		gc.set(gc.DAY_OF_YEAR, 55);

		p = new Person("TAPIS", "Bernard", "TAPISBernard@gmail.com", "TAPIS");
		p.setWebsite("TAPISBernard.exemple.fr");
		p.setBirthDay(gc.getTime());
		p.setPersonParty(test);
		formedPersons.add(p);

		/// CREATION D'UNE DEUXIEME PERSONNE
		gc.set(gc.YEAR, 2010);
		gc.set(gc.DAY_OF_YEAR, 25);

		pp = new Person("ZIDANE", "Zinedine", "ZIDANEZinedine@gmail.com", "ZIDANE");
		pp.setWebsite("ZIDANEZinedine.exemple.fr");
		pp.setBirthDay(gc.getTime());
		pp.setPersonParty(teeest);
		formedPersons.add(pp);

		// SAVE

		for (Party part : formedGroups)
			dao.saveParty(part);
		for (Person pers : formedPersons) {
			dao.savePerson(pers);
		}
	}

	@Test
	  public void testmail() {
		assertEquals(pp.toString(),dao.findByEmail("ZIDANEZinedine@gmail.com").toString());
	  }
	
	@Test
	  public void testFindPersonByFirstName() {
		System.err.println(dao.findAllPersons());
		ArrayList<Person> findPersonByFirstNameLikeTest= new ArrayList<Person>();
		Iterable<Person> testfindPersonByFirstNameLike = dao.findPersonByFirstNameLike("ZIDANE") ;
		
		for (Person pa : testfindPersonByFirstNameLike) {
			findPersonByFirstNameLikeTest.add(pa);
		}
		
		assertTrue(findPersonByFirstNameLikeTest.size()==1);
		assertEquals(pp.toString(),findPersonByFirstNameLikeTest.get(0).toString());
		
	  }
	
	@Test
	  public void testPersonsinParty() {
			
		ArrayList<Person> personsinParty= new ArrayList<Person>();
		Iterable<Person> testAllPersonsInParty = dao.findAllPersonsinParty(dao.findPartyByPartyName("party2").getId()) ;
		
		for (Person pa : testAllPersonsInParty) {
			personsinParty.add(pa);
		}
		
		assertTrue(personsinParty.size()==1);
		assertEquals(pp.toString(),personsinParty.get(0).toString());
	  }
}