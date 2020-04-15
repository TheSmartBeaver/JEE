package springapp.web;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.business.IMessageManager;
import springapp.business.Message;

@Controller()
@RequestMapping("/message")
public class MessageController {

    @Autowired
    IMessageManager messageManger;

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam(required = true) String text) {
        messageManger.add(text);
        return new ModelAndView("message", "messages", messages());
    }

    @RequestMapping(value = "/removeAll")
    public ModelAndView removeAll() {
        int n = messageManger.removeAll();
        logger.info(n + " deleted message(s)");
        return new ModelAndView("message", "messages", messages());
    }

    @RequestMapping(value = "/list")
    public String list() {
        return "message";
    }

    @ModelAttribute("messages")
    public Collection<Message> messages() {
        return messageManger.findAll();
    }
}