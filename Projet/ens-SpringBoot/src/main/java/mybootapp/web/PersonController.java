package mybootapp.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mybootapp.Starter;

import jakarta.validation.Valid;
import mybootapp.authentif.Utilisateur;
import mybootapp.business.IPersonManager;
import mybootapp.dao.DAOPerson;
import mybootapp.dao.PartyRepository;
import mybootapp.dao.PersonRepository;
import mybootapp.generation.Generation;
import mybootapp.model.Party;
import mybootapp.model.Person;

@Controller()
@RequestMapping("/")
public class PersonController {

	/**
	 * Plus d'infos sur form Spring : https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/view.html
	 **/
	
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	
	
	@Autowired
	public DAOPerson dao;
	
	@Autowired
	public DAOPerson dao2;
	
	public DAOPerson getDAO() {
		return dao;
	}
	
	@PostConstruct
	public void init() {
		/*Person p = new Person("Painbeurre","Painbeurrezder","aaa@gmail.com","pswaaa");
		Person p2 = new Person("Painbeurrettete","Painbeurretgerrz","ccc@gmail.com","pswccc");
		Date birthday;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = "2006-02-17";
		try {
			birthday = simpleDateFormat.parse(date1);
			p.setBirthDay(birthday);
			p2.setBirthDay(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		p.setWebsite("monWebite@lol.fr");
		
		System.err.println("Id de p1 :" + p.getId());
		Party party = new Party("Pain");
		
		Party par2 = new Party("Noobs");
		par2.addPersonInParty(p);
		party.addPersonInParty(p2);
		
		dao.saveParty(party);
		dao.saveParty(par2);
		dao.savePerson(p);
		dao2.savePerson(new Person("gg","oo","bbb@gmail.com","pswbbb"));
		
		System.err.println("Id de p2 :" + p2.getId());
		dao.savePerson(p2);
		
		System.err.println(dao.findAllParties().toString());
		System.err.println(dao2.findAllPersons().toString());*/
		
		Generation gener = new Generation();
		gener.generatePersonsAndGroups(dao);
	}

    protected final Log logger = LogFactory.getLog(getClass());
    
    
    //TODO: Remplacer par un truc sélectionnant des groupes
    @ModelAttribute("availableGroups")
    public Set<String> personGroup() {
    	Set<String> personGroup = new HashSet<String>();
    	for(Party p : dao.findAllParties())
    		personGroup.add(p.getPartyName());
        return personGroup;
    }

    
    @ModelAttribute
    public Person newPerson(
            @RequestParam(value = "id", required = false) Long personId) {
        if (personId != null) {
            logger.info("find person " + personId);
            System.err.println("Il y'a une personne dont le nom est à éditer");
            return dao.findPersonById(personId);
        }
        Person p = new Person("","","","");
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
        System.err.println(p.getBirthDay());
        System.err.println("APRES VALIDATE PERSON");
        if (result.hasErrors()) {
        	System.err.println("Il Y A ERREUR");
            return "personForm";
        }
        System.err.println("AVANT SAVE NOUVELLE PERSON");
        /*On récupère le group correspondant dans la BDD*/
        p.setPersonParty(dao.findPartyByPartyName(p.getPersonParty().getPartyName()));
        dao.savePerson(p);
        System.err.println("APRES SAVE NOUVELLE PERSON");
        System.err.println(dao.findAllPersons().toString());
        return "redirect:/group/list";
    }
    
  //TODO: Remplacer par toutes les personnes
    @ModelAttribute("persons")
    Iterable<Person> persons() {
        logger.info("Making list of persons");
        System.err.println("REFAIT la liste des personnes");
        return dao.findAllPersons();
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
	public ModelAndView editPerson(@ModelAttribute @Valid Person p, @RequestParam(value = "id", defaultValue = "-1") Long value,
			HttpSession session) {
    		if(session.getAttribute(ATT_SESSION_USER) == null) {
    			System.err.println("EDIT : Aucune session détecté");
    			return new ModelAndView("Interdit");
    		}
    		Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
    		System.err.println("EDIT : session détecté");
    		if(user.getId().equals(value)) {
    			System.err.println("EDIT : "+user.getId()+"=="+value);
    			return new ModelAndView("personForm");
    			}
    		System.err.println("EDIT : "+user.getId()+"=="+value);
    		return new ModelAndView("Interdit");
	}
    
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
    	//TODO: Comment traiter date mal formée ??
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Party.class, new EditorParty());
    }
}