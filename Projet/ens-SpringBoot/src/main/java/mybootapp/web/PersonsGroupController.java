package mybootapp.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mybootapp.dao.DAOPerson;
import mybootapp.model.Person;

@Controller
@RequestMapping("/")
public class PersonsGroupController {
	
	@Autowired
	public DAOPerson dao;

	protected final Log logger = LogFactory.getLog(getClass());
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	
	@ModelAttribute("personsInGroup")
    Iterable<Person> personsInGroup(@RequestParam(value = "id", required = false) Long groupId) {
        logger.info("Making list of persons in group : "+groupId);
        return dao.findAllPersonsinParty(groupId);
    }
	
	@ModelAttribute("selectedGroup")
    String groupSelected(@RequestParam(value = "id", required = false) Long groupId) {
        return dao.findPartyById(groupId).getPartyName();
    }
	
	@RequestMapping(value = "/personsGroup/list", method = RequestMethod.GET)
    public String listPersons() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
        logger.info("List of persons in Group");
        if(session.getAttribute( ATT_SESSION_USER )== null)
        	return "personsGroupList";
        else
        	return "restreint/personsGroupListRestreint";
    }
}
