package mybootapp.business;

import java.util.Collection;

import mybootapp.model.Person;
import mybootapp.model.Product;

public interface IPersonManager {

    Collection<Person> findAll();

    void save(Person p);

    Person find(long id);

}
