package mybootapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import mybootapp.model.Party;
import mybootapp.model.Person;

@Service
public class DAOPerson implements IPersonDao {

	@Autowired
	PersonRepository repo;
	@Autowired
	PartyRepository repoParty;
	
	@Override
	public void savePerson(Person p) {
		repo.save(p);
	}
	@Override
	public void saveParty(Party p) {
		repoParty.save(p);
	}
	@Override
	public Iterable<Party> findAllParties() {
		return repoParty.findAll();
	}

	@Override
	public Iterable<Person> findAllPersons() {
		return repo.findAll();
	}

	@Override
	public Person findByEmail(String email) {
		List<Person> pList = repo.findByMail(email);
		if(pList.size()>1) {
			System.err.println("Problème mail pas unique");
			System.exit(1);
		}
		if(pList.size()<1)
			return null;
		return pList.get(0);
	}
	@Override
	public Person findById(Long id) {
		return repo.findById(id).get();
	}
	
}
