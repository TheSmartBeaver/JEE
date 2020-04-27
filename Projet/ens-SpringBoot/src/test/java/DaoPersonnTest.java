import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.*;
import mybootapp.dao.DAOPerson;
import mybootapp.dao.PartyRepository;
import mybootapp.dao.PersonRepository;
import mybootapp.model.Party;
import mybootapp.model.Person;

public class DaoPersonnTest extends TestCase {

  public void test() throws Exception {
	  
	 DAOPerson dao = new DAOPerson();
	
	ArrayList<Party> formedGroups = new ArrayList<>();
	ArrayList<Person> formedPersons = new ArrayList<>();
	
	ArrayList<Party> groups = new ArrayList<>();
	ArrayList<Person> persons = new ArrayList<>();
	
	Party test = new Party ("test 1");
	Party teeest = new Party ("test 2");
	formedGroups.add(test);
	formedGroups.add(teeest);
	
	groups.add(test);
	
	
	GregorianCalendar gc = new GregorianCalendar();
	gc.set(gc.YEAR, 2020);
	gc.set(gc.DAY_OF_YEAR, 55);
	
	Person p = new Person("TAPIS","Bernard","TAPISBernard@gmail.com","TAPIS");
	p.setWebsite("TAPISBernard.exemple.fr");
	p.setBirthDay(gc.getTime());
	p.setPersonParty(test);
	formedPersons.add(p);
	
	gc.set(gc.YEAR, 2010);
	gc.set(gc.DAY_OF_YEAR, 25);
	
	Person pp = new Person("ZIDANE","Zinedine","ZIDANEZinedine@gmail.com","ZIDANE");
	pp.setWebsite("ZIDANEZinedine.exemple.fr");
	pp.setBirthDay(gc.getTime());
	pp.setPersonParty(teeest);
	pp.setPersonParty(test);
	formedPersons.add(pp);
	
	for(Party part : formedGroups)
		dao.saveParty(part);
	for(Person pers : formedPersons) {
		//System.err.println("J'insère "+pers.toString());
		dao.savePerson(pers);
	}
	
	/////////TEST findAllParties et finAllPersons
	
	Iterable<Party> testAllParty = dao.findAllParties();
		for (Party pa : testAllParty) {
			groups.add(pa);
		}
	Iterable<Person> testAllPerson = dao.findAllPersons();
		for (Person pa : testAllPerson) {
			persons.add(pa);
		}
	
	assertEquals(formedGroups,groups);
	assertEquals(formedPersons,persons);
	
	/////////Test finByEmail
	
    assertEquals(p,dao.findByEmail("ZIDANEZinedine@gmail.com"));
    assertEquals(p,dao.findByEmail("TAPISBernard@gmail.com"));
    
    ////////TEST findAllPersonsinParty
    teeest.setId(1);
    ArrayList<Person> personsinParty= new ArrayList<Person>();
    Iterable<Person> testAllPersonsInParty = dao.findAllPersonsinParty(teeest.getId()) ;
	for (Person pa : testAllPersonsInParty) {
		personsinParty.add(pa);
	}
	
	ArrayList<Person> personsinPartytest= new ArrayList<Person>();
	personsinPartytest.add(p);
	personsinPartytest.add(pp);
	
	assertEquals(personsinPartytest,personsinParty);
	
	//////Test findPartyBypartName
	
	assertEquals(test,dao.findPartyByPartyName("test 1"));
	
	///////Test findPartyByPartyNameLike
	ArrayList<Party> findPartyByPartyNameLikeTest= new ArrayList<Party>();
    Iterable<Party> testfindPartyByPartyNameLike = dao.findPartyByPartyNameLike("test 1") ;
	for (Party pa : testfindPartyByPartyNameLike) {
		findPartyByPartyNameLikeTest.add(pa);
	}
	
	ArrayList <Party> findPartyByPartyNameLike = new ArrayList<Party>();
	findPartyByPartyNameLike.add(test);
	
	assertEquals(findPartyByPartyNameLike,findPartyByPartyNameLikeTest);
    
	
	///////TEST  FindPersonByFirstNameLike
  }
}