package mybootapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthentificationController {


    @RequestMapping(value = "/user/show")
    public String show() {
    	//RIENNNNNNNNNNNNNN
        return "user";
    }

    @RequestMapping(value = "/user/login")
    public String login() {
        return "connexion";
    }

    @RequestMapping(value = "/user/logout")
    public String logout() {
    	//RIENNNNNNNNNN POUR L'instant
        return "user";
    }
}
