package springapp.web;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ByeConstraintValidator implements ConstraintValidator<Bye, String> {

    @Override
    public void initialize(Bye arg0) {
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (arg0.contains("bye")) {
        	System.err.println("YEEEEEESSSSSSSSSSS");
            return true;
            }
        System.err.println("WHHHHHHHHHHHHHHHHHHHHHHAAAT");
        return false;
    }
}
