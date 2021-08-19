package util.Validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentDataValidation {


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

    //phone number validation for 10 digits, start with zero, next value from 1-9 and rest 8 digits from 0-9
    public static  final Pattern VALIDPHONENO = Pattern.compile("^[0][1-9][0-9]{8}$");

    public static boolean isValidPhoneNo(String phone){

        Matcher matcher = VALIDPHONENO.matcher(phone);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public static void isValidPhoneNo(String phone, Label label, String validationText) {

        if ((!isValidPhoneNo(phone)) && (!phone.isEmpty())) {
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

}
