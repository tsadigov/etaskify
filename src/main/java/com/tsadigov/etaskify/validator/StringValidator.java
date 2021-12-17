package com.tsadigov.etaskify.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static com.tsadigov.etaskify.config.Constants.EMAIL_PATTERN;

public class StringValidator {

    public static boolean isAlphaNumeric(String data) {

        if (data.length() > 1) {
            for (int i = 0; i < data.length(); i++) {
                char c = data.charAt(i);
                if (!Character.isLetterOrDigit(c))
                    return false;
            }
            return true;
        }

        return false;
    }

    public static boolean isValidPasswordFormat(String password) {
        if (isAlphaNumeric(password) && password.length() > 5) return true;
        return false;
    }

    public static boolean isValidEmailFormat(String email) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }

}
