package util;

import java.util.regex.Pattern;

public class Validator {
    public static boolean validateEmail(String email) {
        Pattern emailRegEx = Pattern.compile(".+@.+.com");
        return emailRegEx.matcher(email).matches();
    }
}
