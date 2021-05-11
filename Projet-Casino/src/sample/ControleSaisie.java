package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControleSaisie {

    private static Matcher matcher;
    private static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
    private static final String password = "^[A-Za-z0-9]+$";
    private static Pattern patternPassword = Pattern.compile(password);

    public static boolean isString(String text){
        if(text.matches("^[a-zA-Z]")){
            return true;
        }
        return false;
    }

    public static boolean isNull(String text){
        if(text == ""){
            return true;
        }
        return false;
    }

    public static boolean isUsername(String text){
        if(text.matches("^[A-Za-z0-9]+$+")){
            return true;
        }
        return false;
    }

    public static boolean DateNullCS(String date){
        if(date == ""){
            return true;
        }
        return false;
    }

    public static boolean address(String text){
        if(text.matches("^[A-Za-z0-9]+$")){
            return true;
        }
        return false;
    }

    public static boolean isCin(String text){
        if(text.matches("^[0-9]+$") && text.length() == 8){
            return true;
        }
        return false;
    }

    public static boolean isTel(String text){
        if(text.matches("^[0-9]+$") && text.length() == 8){
            return true;
        }
        return false;
    }

    public static boolean validEmail(final String hex){
        matcher = patternEmail.matcher(hex);
        return matcher.matches();
    }

    public static boolean validPassword(final String hex){
        matcher = patternPassword.matcher(hex);
        return matcher.matches();
    }
}
