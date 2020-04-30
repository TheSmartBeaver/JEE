package mybootapp.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mybootapp.dao.PersonService;
import mybootapp.model.Party;

@Controller()
@RequestMapping("/")
public class GroupController {

    @Autowired
    PersonService dao;

    protected final Log logger = LogFactory.getLog(getClass());
    
    

    @ModelAttribute("availableGroups")
    Iterable<Party> availableGroups() {
        logger.info("Making list of groups");
        return dao.findAllParties();
    }
    
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    public String listProducts() {
        logger.info("List of groups");
        return "groupList";
    }
    
}