package springapp.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/tests")
public class Adresse {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/voir/{param}/{i}", method = RequestMethod.GET)
	public ModelAndView voir(@PathVariable("param") Integer param, @PathVariable("i") String i) {
		logger.info("Running param controler with param=" + param);
	    return new ModelAndView("hello", "now", param).addObject("i", i);
	}
}