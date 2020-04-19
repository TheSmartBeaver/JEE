package mybootapp.dao;

import java.util.HashMap;
import java.util.Map;

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
	Map<String, Object> model = new HashMap<String, Object>();

	@PostConstruct
	public void init() {
		Person p = new Person("Painbeurre","Painbeurrezder","Painbeurretheygtgr");
		Party party = new Party("Pain");
		//party.addPersonInGroup(p);
		
		repoParty.save(party);
		
		repoParty.save(new Party("Noobs"));
		repo.save(p);
		repo.save(new Person("Painbeurrettete","Painbeurretgerrz","Painbeurretehygrg"));
	}

	@RequestMapping("/person/list")
	public ModelAndView listPersons() {
		final var result = repo.findAll();
		System.err.println(result+"/person/list");
		//model.put("personsResult", );
		model.put("persons", result);
		ModelAndView mAv = new ModelAndView("person", "model", model);
		System.err.println(mAv+"/person/list");
		return mAv;
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
		System.err.println(result+" /person/find");
		ModelAndView mAv = new ModelAndView("person", "persons", result);
		System.err.println(mAv+" /person/find");
		return mAv;
	}
	
	@RequestMapping("/party/list")
	public ModelAndView affPainGroup(String partyName) {
		final var result = repoParty.findAll();
		System.err.println(result+"/party/list");
		//model.put("personsResult", );
		model.put("partys", result);
		ModelAndView mAv = new ModelAndView("person", "model", model);
		System.err.println(mAv+"/party/list");
		return mAv;
	}

}