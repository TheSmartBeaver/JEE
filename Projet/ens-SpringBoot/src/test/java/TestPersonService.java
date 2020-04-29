import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import mybootapp.Starter;
import mybootapp.dao.DAOPerson;
import mybootapp.dao.IPersonDao;
import mybootapp.dao.PersonRepository;
import mybootapp.model.Party;
import mybootapp.model.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class TestPersonService {
 
	 
    @Autowired
    private PersonRepository repoPerson;
    @Autowired
    private DAOPerson servPerson;
    
 
    @Test
    public void simpleTestOfDAO() {
    	repoPerson.deleteAll();
    	if(repoPerson==null)
    		fail();
    	System.err.println("personRepo "+repoPerson);
    	repoPerson.save(new Person("aa","gg,","mail","psw"));
    	System.err.println(repoPerson.findAll());
    }
    
    @Test
    public void simpleTestOfService() {
    	repoPerson.deleteAll();
    	if(servPerson==null)
    		fail();
    	System.err.println("personSERVICE "+servPerson);
    	servPerson.savePerson(new Person("aa","gg,","mail","psw"));
    	System.err.println(servPerson.findAllPersons());
    }
    // write test cases here
}