package mybootapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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

import jakarta.validation.Valid;
import mybootapp.authentif.Utilisateur;
import mybootapp.dao.PersonService;
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
	public PersonService dao;
	
	@Autowired
	public PersonService dao2;
	
	public PersonService getDAO() {
		return dao;
	}
	
	@PostConstruct
	public void init() {
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

    /*PLUS NECESSAIRE ???*/
    @ModelAttribute
    public Person newPerson(
            @RequestParam(value = "id", required = false) Long personId) {
        if (personId != null) {
            logger.info("find person " + personId);
            logger.info("Il y'a une personne dont le nom est à éditer");
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
    
    
    @Autowired
    ValidatorPerson validator;

    @RequestMapping(value = "/person/edit", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute @Valid Person p, BindingResult result) {
        validator.validate(p, result);
        System.err.println(p.getBirthDay());
        if (result.hasErrors()) {
            return "personForm";
        }
        /*On récupère le group correspondant dans la BDD*/
        p.setPersonParty(dao.findPartyByPartyName(p.getPersonParty().getPartyName()));
        dao.savePerson(p);
        logger.info("Une personne a été éditer !");
        return "redirect:/group/list";
    }
    
  //TODO: Remplacer par toutes les personnes
    //PLUS NECESSAIRE ???
    @ModelAttribute("persons")
    Iterable<Person> persons() {
        logger.info("Making list of persons");
        return dao.findAllPersons();
    }
    
    @RequestMapping(value = "/person/edit", method = RequestMethod.GET)
	public ModelAndView editPerson(@ModelAttribute @Valid Person p, @RequestParam(value = "id", defaultValue = "-1") Long value,
			HttpSession session) {
    		if(session.getAttribute(ATT_SESSION_USER) == null) {
    			logger.info("Aucune session détecté !");
    			return new ModelAndView("Interdit");
    		}
    		Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
    		logger.info("session détecté !");
    		if(user.getId().equals(value)) {
    			System.err.println("EDIT : "+user.getId()+"=="+value);
    			return new ModelAndView("personForm");
    			}
    		logger.info("edition interdite!");
    		return new ModelAndView("Interdit");
	}
    
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
    	//TODO: Comment traiter date mal formée ??
    	try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    	}
    	catch(Exception e) {
    		throw new IllegalArgumentException("Erreur dans le format de la date :(");
    	}
    }
    
    @InitBinder
    public void initBinder(WebDataBinder b) {
        b.registerCustomEditor(Party.class, new EditorParty());
    }
}