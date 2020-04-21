package mybootapp.authentif;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// sudo lsof -i -P -n | grep LISTEN
@Controller()
@RequestMapping("/")
public class RestreintController {

	@RequestMapping(value = "/acces/restreint", method = RequestMethod.GET)
	public String goRestreint() {
		System.err.println("Je redirige vers accès restreint");
	    return "restreint/accesRestreint";
	}

}
