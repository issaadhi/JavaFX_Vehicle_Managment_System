package util.Validation;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidation {
    public static boolean TextFieldNotEmpty(TextField textField){
        //returning text fields empty as default value
        boolean returnVal = false;
        if(textField.getText() != null  && !textField.getText().isEmpty()){
            returnVal = true;
        }
        return returnVal;
    }
    public static void TextFieldNotEmpty(TextField textField, Label label, String validationText){

        if(!TextFieldNotEmpty(textField)){
            label.setText(validationText);
        }

    }

    //Checking TextAreas for not empty with same name
    public static boolean TextAreaNotEmpty(TextArea textArea){
        //returning text area empty as default value
        boolean returnVal = false;
        if(textArea.getText() != null  && !textArea.getText().isEmpty()){
            returnVal = true;
        }
        return returnVal;
    }

    //Checking Strings for not empty with same name

    public static boolean TextFieldNotEmpty(String stringField){
        //returning string fields empty as default value
        boolean returnVal = false;
        if(stringField != null  && !stringField.isEmpty()){
            returnVal = true;
        }
        return returnVal;
    }


    public static void TextFieldNotEmpty(PasswordField passwordField, Label label, String validationText){

        if(!TextFieldNotEmpty(passwordField)){
            label.setText(validationText);
        }

    }





    public static boolean PasswordFieldNotEmpty(PasswordField passwordField){
        //returning integer fields empty as default value
        boolean returnVal = false;
        if(passwordField.getText() != null  && !passwordField.getText().isEmpty()){
            returnVal = true;
        }
        return returnVal;
    }

    public static void PasswordFieldNotEmpty(PasswordField passwordField, Label label, String validationText){

        if(!PasswordFieldNotEmpty(passwordField)){
            label.setText(validationText);
        }

    }
    public static boolean PasswordFieldMatch(PasswordField passwordField, PasswordField ConfirmPasswordField){
        //returning integer fields empty as default value
        boolean returnVal = false;
        if(passwordField.getText().equals(ConfirmPasswordField.getText())){
            returnVal = true;
        }
        return returnVal;
    }


    //email validation
    public static final Pattern VALIDEMAIL =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean isValidEmail(String emailStr) {
        boolean returnVal = false;
        Matcher matcher = VALIDEMAIL.matcher(emailStr);

        if(matcher.find()){
            returnVal = true;
        }
        return returnVal;
    }

    public static void isValidEmail(String emailStr, Label label, String validationText) {

        if((!isValidEmail(emailStr))&& (!emailStr.isEmpty())){
            label.setText(validationText);
        }
    }


    //checking for maximum length
    public static boolean isValidMaximumLength(String data, int maxLength){
        boolean returnVal = true;
        if(data.length() > maxLength){
            returnVal = false;
        }
        return returnVal;
    }
    public static void isValidMaximumLength(String data, int maxLength, Label label, String validationText){
        if(!isValidMaximumLength(data,maxLength)){
            label.setText(validationText);
        }
    }
    //checking for minimum length
    public static boolean isValidMinimumLength(String data, int minLength){
        boolean returnVal = true;
        if((data.length() < minLength) && (!data.isEmpty())){
            returnVal = false;
        }
        return returnVal;
    }
    public static void isValidMinimumLength(String data, int minLength, Label label, String validationText){

        if(!isValidMinimumLength(data, minLength)){
            label.setText(validationText);
        }
    }

    public static void PasswordFieldMatch(PasswordField passwordField, PasswordField ConfirmPasswordField, Label passwordLabel, Label confirmPasswordLabel,String validationText){

        if(!PasswordFieldMatch(passwordField, ConfirmPasswordField)){
            passwordLabel.setText(validationText);
            confirmPasswordLabel.setText(validationText);
        }

    }
}
