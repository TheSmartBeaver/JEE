package mybootapp.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.model.Person;

@Controller
@RequestMapping("/")
public class PersonControler {

	/*
	 * Injection de la DAO de manipulation des cours.
	 */
	@Autowired
	PersonRepository repo;

	@PostConstruct
	public void init() {
		Person p = new Person("Painbeurrette");
		//p.setId(1L);
		repo.save(p);
		p = new Person("Painbeurre");
		//p.setId(2L);
		repo.save(p);
		p = new Person("Painbeurrettete");
		//p.setId(3L);
		repo.save(p);
	}

	@RequestMapping("/person/list")
	public ModelAndView listPersons() {
		return new ModelAndView("person", "persons", repo.findAll());
	}

	@RequestMapping("/person/new")
	public String newPerson() {
		final var person = new Person("new");
		repo.save(person);
		return "redirect:/person/list";
	}

	@RequestMapping("/person/find")
	public ModelAndView findCourses(String firstName) {
		final var result = repo.findByFirstName(firstName);
		return new ModelAndView("course", "courses", result);
	}

}