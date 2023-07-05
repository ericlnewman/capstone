package com.success.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    
	 private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        try {	
    	return isValidPassword(password);
        }catch(Exception e) {
            log.error("Unable validate password.", e);
            return false;
        }
   
    }
    
    /* *************************************************************************************************
     * Regular expressions: A regular expression (regex) defines a search pattern for strings.
     * Quantifiers define how often an element can occur:  ?, *, + and {}
     * a $ signifies the end of the expression
     * a * means it occurs zero or more times, while . will match any character
     * so .*  finds any character sequence. A ? occurs no or one times, so *?
     * tries to find the smallest match. This makes the regular expression stop
     * and at the first match.
     * anything within [] is a set definition, can match the letters
     * anything within [1-4] or [a-c] sets a range for the definition
     *
     * Example: "(?.=*[a-z])(?.=*[A-Z].+$"
     * There are two parts to this within the () and both are positive look ahead assertion.
     * The first (?=.*[a-z]). Within this the ?= can be read as 'Look ahead and assert that the
     * following pattern exists', while .* can be read as 'match any number of characters
     * (except a newline)'. The characters are within the range of all 26 lower case letters.
     * The same applies for the second assertion.
     **************************************************************************************************/
    /*
     * This validates a password to ensure it is at least 8 characters long, has an upper and lower case letter, contains a special character.
     */
   public boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 25) {
        	 log.error("Not the right length.");
             return false;
        	
        }

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z]).+$")) {
        	 log.error("Not the an upper or lower case letter.");
            return false;
        }

        if (!password.matches("(?=.*\\d).+$")) {
        	 log.error("No number.");
            return false;
        }

        if (!password.matches("(?=.*[!@#_+=$%^&*:?\\-]).+$")) {
        	 log.error("No special character.");
            return false;
        }

        return true;
    }

}
