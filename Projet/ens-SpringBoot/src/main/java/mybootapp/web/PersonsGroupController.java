package mybootapp.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mybootapp.dao.DAOPerson;
import mybootapp.model.Person;

@Controller
@RequestMapping("/")
public class PersonsGroupController {
	
	@Autowired
	public DAOPerson dao;

	protected final Log logger = LogFactory.getLog(getClass());
	
	@ModelAttribute("personsInGroup")
    Iterable<Person> personsInGroup(@RequestParam(value = "id", required = false) Long groupId) {
        logger.info("Making list of persons in group : "+groupId);
        return dao.findAllPersonsinParty(groupId);
    }
	
	@RequestMapping(value = "/personsGroup/list", method = RequestMethod.GET)
    public String listPersons() {
        logger.info("List of persons in Group");
        return "personsGroupList";
    }
}
