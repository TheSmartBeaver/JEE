package springapp.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Service("/hello.htm")
public class HelloController implements Controller {

    protected final Log logger = LogFactory.getLog(getClass());

    public ModelAndView handleRequest(HttpServletRequest request, //
            HttpServletResponse response) throws ServletException, IOException {

        String now = (new Date()).toString();
        String message = "This is a test message...";
        logger.info("Returning hello view with " + now);

        return new ModelAndView("hello", "now", now).addObject("message", message);
    }

}