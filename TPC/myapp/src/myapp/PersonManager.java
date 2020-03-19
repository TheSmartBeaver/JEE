package myapp;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PersonManager {

    final private Map<Integer, Person> persons;

    public PersonManager() {
        persons = Collections.synchronizedMap(new HashMap<>());
        save(new Person(100, "Paul", "paul@hello.fr"));
        save(new Person(200, "Laure", "laure@univ-amu.fr"));
    }

    public Person findPerson(Integer id) {
        Person p = persons.get(id);
        return (p == null) ? null : new Person(p);
    }

    public Collection<Person> findAll() {
        Collection<Person> result = new LinkedList<>();
        persons.values().forEach((Person p) -> {
            result.add(new Person(p));
        });
        return result;
    }

    public void save(Person p) {
        Person saved = new Person(p);
        persons.put(saved.getId(), saved);
    }

    public boolean check(Person p) {
        String mail = p.getMail();
        String nom=p.getName();
        if (nom=="" || isValidEmailAddress(mail)==false){
            p.setErrorMail("Le mail est incorrect");
            p.setErrorName("Le nom renseigné est incorrect");
            return false;
        }
        return true;
        
    }
    
    public static boolean isValidEmailAddress(String email) {
    	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    	      return email.matches(regex);
    	}

}