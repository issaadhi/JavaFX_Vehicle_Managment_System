package util.Validation;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VehicleDataValidation {
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
