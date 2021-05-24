package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputControl {

    private static Matcher matcher;
    private static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
    private static final String PASSWORD_PATTERN = "^[A-Za-z0-9]+$";
    private static Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Méthode qui vérifie si texte correspond au format d'un nom d'utilisateur
     **/
    public static boolean isUsername(String text){
        if(text.matches("^[A-Za-z0-9]+$+")){
            return true;
        }
        return false;
    }

    /**
     * Méthode qui vérifie si texte correspond au format d'un email
     **/
    public static boolean validEmail(final String text){
        matcher = patternEmail.matcher(text);
        return matcher.matches();
    }

    /**
     * Méthode qui vérifie si texte correspond au format d'un mot de passe
     **/
    public static boolean validPassword(final String text){
        matcher = patternPassword.matcher(text);
        return matcher.matches();
    }
}
