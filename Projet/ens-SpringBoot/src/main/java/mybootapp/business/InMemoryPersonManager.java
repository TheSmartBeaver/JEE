package mybootapp.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import mybootapp.model.Person;
import mybootapp.model.Product;
import mybootapp.model.ProductCode;

@Service("personManager")
public class InMemoryPersonManager implements IPersonManager {

    final Map<Long, Person> persons;
    int maxId = 0;

    public InMemoryPersonManager() {
        this.persons = new HashMap<Long, Person>();
        Person p1 = new Person("","","","");
        //p1.setId(newId++);
        p1.setBirthDay(null);
        p1.setMail("");
        p1.setPersonParty(null);
        p1.setWebsite("");
        Person p2 = new Person("","","","");
        //p1.setId(newId++);
        p2.setBirthDay(null);
        p2.setMail("");
        p2.setPersonParty(null);
        p2.setWebsite("");
        maxId = 300;
    }

    @Override
    public Collection<Person> findAll() {
        return persons.values();
    }

    @Override
    public void save(Person p) {
        persons.put(p.getId(), p);
    }

    @Override
    public Person find(long id) {
        Person p = persons.get(id);
        if (p == null) {
            throw new IllegalArgumentException("no product " + id);
        }
        return p;
    }
}
