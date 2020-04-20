package mybootapp.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import mybootapp.business.IPersonManager;
import mybootapp.dao.PartyRepository;
import mybootapp.dao.PersonRepository;
import mybootapp.model.Party;
import mybootapp.model.Person;

@Controller()
@RequestMapping("/")
public class PersonController {

	@Autowired
	PersonRepository repo;
	@Autowired
	PartyRepository repoParty;
	Map<String, Object> model = new HashMap<String, Object>();

	@PostConstruct
	public void init() {
		Person p = new Person("Painbeurre","Painbeurrezder","Painbeurretheygtgr");
		System.err.println("Id de p1 :" + p.getId());
		Party party = new Party("Pain");
		
		Party par2 = new Party("Noobs");
		par2.addPersonInParty(p);
		
		repoParty.save(party);
		repoParty.save(par2);
		repo.save(p);
		
		Person p2 = new Person("Painbeurrettete","Painbeurretgerrz","Painbeurretehygrg");
		System.err.println("Id de p2 :" + p2.getId());
		repo.save(p2);
		
		System.err.println(repo.findAll().toString());
	}

    protected final Log logger = LogFactory.getLog(getClass());
    
    
    //TODO: Remplacer par un truc sélectionnant des groupes
    @ModelAttribute("availableGroups")
    public Map<String, String> personGroup() {
        Map<String, String> groups = new LinkedHashMap<>();
        groups.put("group1", "Group 1");
        groups.put("group2", "Group 2");
        return groups;
    }

    
    @ModelAttribute
    public Person newPerson(
            @RequestParam(value = "id", required = false) Long personId) {
        if (personId != null) {
            logger.info("find person " + personId);
            System.err.println("Il y'a une personne dont le nom est à éditer");
            return repo.findById(personId).get();
        }
        Person p = new Person("","","");
        //p.setId(newId++);
        p.setBirthDay(null);
        p.setMail("");
        p.setPersonParty(null);
        p.setWebsite("");
        logger.info("new person = " + p);
        return p;
    }
    
    @RequestMapping(value = "/person/list", method = RequestMethod.GET)
    public String listPersons() {
        logger.info("List of persons");
        return "personsList";
    }
    
    @Autowired
    ValidatorPerson validator;

    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
    	System.err.println("AVANT VALIDATE PERSON");
        validator.validate(p, result);
        if (result.hasErrors()) {
            return "personForm";
        }
        System.err.println("AVANT SAVE NOUVELLE PERSON");
        repo.save(p);
        System.err.println("APRES SAVE NOUVELLE PERSON");
        System.err.println(repo.findAll().toString());
        return "redirect:/person/list";
    }
    
  //TODO: Remplacer par toutes les personnes
    @ModelAttribute("persons")
    Iterable<Person> persons() {
        logger.info("Making list of persons");
        System.err.println("REFAIT la liste des personnes");
        return repo.findAll();
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
	public ModelAndView editPerson(@ModelAttribute @Valid Person p, @RequestParam(value = "id", defaultValue = "-1") Long value) {
		if(value == -1) //return "personForm";
			return new ModelAndView("personForm");
		else {
			//Person p2 = repo.findById(value).get();
			//return new ModelAndView("personForm").addObject("firstname",p2.getFirstName());
			return new ModelAndView("personForm");
		}
	}
    
    /*@InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(ProductCode.class, new EditorProductCode());
    }*/
}