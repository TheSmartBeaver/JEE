package mybootapp.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.validation.Valid;
import mybootapp.model.Person;

@Controller()
@RequestMapping("/")
public class EditPersonController {
	
	
	
	@RequestMapping(value = "/person/edit", method = RequestMethod.GET)
	public String editPerson(@ModelAttribute @Valid Person p) {
	    return "personForm";
	}
}
