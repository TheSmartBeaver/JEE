package springapp.web;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/tests")
public class Counter {
	private int i = 0;
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/counter", method = RequestMethod.GET)
    public ModelAndView count() {
        String now = (new Date()).toString();
        logger.info("Running " + this);
        i +=1;
        return new ModelAndView("hello", "now", now).addObject("i", i);
    }
}
