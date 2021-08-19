package util.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class PaymentAlertMsg {

    public static void insertSuccesfully(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successfull..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Added Successfully.. " );
        msg.showAndWait();

    }

    public static void insertionFailed( String text){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }


    public static void updateSuccesfully(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successful..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Updated Successfully.. " );
        msg.showAndWait();
    }

    public static void updateFailed(String text){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text +" not Updated, Try Again!.." );
        msg.showAndWait();
    }


    public static void deleteSuccesfull(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successfull..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Deleted Successfully.. " );
        msg.showAndWait();

    }

    public static void deleteFailed(String text){
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occured!..");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }

    public static Optional<ButtonType> deleteConfirmation(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete selected " +text + "?");
        Optional<ButtonType> action = alert.showAndWait();
        return action;
    }

    public static void selectRowToDelete(String text){
        Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
        successMsg.setTitle("Please Select..");
        successMsg.setHeaderText(null);
        successMsg.setContentText("Please Select a "+ text + " record to Delete..");
        successMsg.showAndWait();
    }
}
