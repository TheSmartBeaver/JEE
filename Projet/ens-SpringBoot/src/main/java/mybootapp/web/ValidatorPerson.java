package mybootapp.web;

import java.util.Date;

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
                "person.firstName");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
                "person.lastName");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "person.password");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mail",
                "person.mail");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website",
                "person.website");
        
        Date birthday = person.getBirthDay();
        System.err.println("birthday = "+birthday);
        
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay",
                "person.birthDay");*/
        
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "party",
                "person.party");*/


        /*if (!(product.getPrice() > 0.0)) {
            errors.rejectValue("price", "product.price.too.low");
        }
        
        ProductCode code = product.getCode();
        if (code != null) {
            if (!code.getBase().matches("[A-Z]")) {
                errors.rejectValue("code", "product.code.base");
            }
            if (!(code.getNumber() >= 1000 && code.getNumber() <= 9999)) {
                errors.rejectValue("code", "product.code.number");
            }
        }*/
    }

}