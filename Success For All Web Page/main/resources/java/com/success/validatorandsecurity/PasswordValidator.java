package com.success.validatorandsecurity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        	
    	return isValidPassword(password);
   
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
            return false;
        }

        if (!password.matches("(?=.*[a-z])(?=.*[A-Z]).+$")) {
            return false;
        }

        if (!password.matches("(?=.*\\d).+$")) {
            return false;
        }

        if (!password.matches("(?=.*[!@#_+=$%^&*:?\\-]).+$")) {
            return false;
        }

        return true;
    }

}
