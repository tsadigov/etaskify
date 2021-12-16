package com.tsadigov.etaskify.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringValidator {

    public static boolean isAlphaNumeric(String data) {

        if (data.length() > 1) {
            for (int i=0;i<data.length();i++){
                char c = data.charAt(i);
                if(!Character.isLetterOrDigit(c))
                    return false;
            }
            return true;
        }

        return false;
    }


}
