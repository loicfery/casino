package sample;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
        if(text.matches("^[A-Za-z0-9]+$+") && text.length() >= 5){
            return true;
        }
        return false;
    }

    /**
     * Méthode qui vérifie si texte correspond au format d'un email
     **/
    public static boolean validEmail(final String text){
        if(text.length() >= 5) {
            matcher = patternEmail.matcher(text);
            return matcher.matches();
        }
        else{
            return false;
        }
    }

    /**
     * Méthode qui vérifie si texte correspond au format d'un mot de passe
     **/
    public static boolean validPassword(final String text){
        if(text.length() >= 5) {
            matcher = patternPassword.matcher(text);
            return matcher.matches();
        }
        else {
            return false;
        }
    }

    public static void inputControlNumber(final String oldValue, final String newValue, TextField textField){
        final Pattern wholeNumberPattern = Pattern.compile("\\d*");
        if (!wholeNumberPattern.matcher(newValue).matches()){
            textField.setText(oldValue);
        }
    }
}
