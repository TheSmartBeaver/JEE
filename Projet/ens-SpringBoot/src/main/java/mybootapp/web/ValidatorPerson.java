package mybootapp.web;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import mybootapp.model.Person;

@Service
public class ValidatorPerson implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
                "person.firstName","Merci de saisir un Nom");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "person.lastName","Merci de saisir un prénom");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "person.password","Merci de saisir un mdp");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail",
                "person.mail","Merci de saisir un mail");
        
        String regexMail = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regexMail);
        Matcher matcher = pattern.matcher(person.getMail());
        if(!matcher.matches())
        	errors.rejectValue("mail", "person.mail", "Mail mal formé");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website",
                "person.website","Merci de saisir un site Web");
        
        String regexWeb = "^[A-Za-z0-9+_.-]+(.+)$";
        pattern = Pattern.compile(regexWeb);
        matcher = pattern.matcher(person.getWebsite());
        if(!matcher.matches())
        	errors.rejectValue("website", "person.website","Site web mal formé");
        
        try {
        Date birthday = person.getBirthDay();
        System.err.println("birthday = "+birthday);
        }
        catch(Exception e) {
    		throw new IllegalArgumentException("Erreur dans le format de la date :(");
    	}
       
        if(person.getPersonParty().getPartyName().equals(""))
        	errors.rejectValue("personParty", "person.personParty","Veuillez sélectionner un groupe !");

    }

}