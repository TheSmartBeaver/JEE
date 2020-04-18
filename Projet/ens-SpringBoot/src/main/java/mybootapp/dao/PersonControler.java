package mybootapp.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import mybootapp.model.Group;
import mybootapp.model.Person;
import mybootapp.model.Party;

@Controller
@RequestMapping("/")
public class PersonControler {

	/*
	 * Injection de la DAO de manipulation des cours.
	 */
	@Autowired
	PersonRepository repo;
	@Autowired
	PartyRepository repoParty;

	@PostConstruct
	public void init() {
		Person p = new Person("Painbeurre","Painbeurrezder","Painbeurretheygtgr");
		Party party = new Party("Pain");
		//party.addPersonInGroup(p);
		
		repoParty.save(party);
		repo.save(p);
		repo.save(new Person("Painbeurrettete","Painbeurretgerrz","Painbeurretehygrg"));
	}

	@RequestMapping("/person/list")
	public ModelAndView listPersons() {
		return new ModelAndView("person", "persons", repo.findAll());
	}

	@RequestMapping("/person/new")
	public String newPerson() {
		final var person = new Person("newrtzg","newyhtegr","newrtgz");
		repo.save(person);
		return "redirect:/person/list";
	}

	@RequestMapping("/person/find")
	public ModelAndView findPersons(String firstName) {
		final var result = repo.findByFirstNameLike("%" + firstName + "%");
		return new ModelAndView("person", "persons", result);
	}
	
	@RequestMapping("/party/affiche")
	public ModelAndView affPainGroup(String partyName) {
		final var result = repoParty.findByPartyNameLike("%" + partyName + "%");
		System.err.println("WAAAAAAAAAAAAAAAAAAAAAAAA");
		return new ModelAndView("party", "partys", result);
	}

}