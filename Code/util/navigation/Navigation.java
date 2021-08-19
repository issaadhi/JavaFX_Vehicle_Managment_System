package util.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.User;
import util.authenticate.Authentication;

import java.io.IOException;

public class Navigation {
    public void logout(AnchorPane baseAnchorPane){
        try{
            User user = null;
            Authentication.setAuthenticatedUser(user);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/login.fxml"));
            baseAnchorPane.getChildren().setAll(pane);
        }catch(IOException | NullPointerException ex){
            System.out.println("Something went wrong.");
        }
    }


    public void loadAppHome(AnchorPane baseAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/homeBase.fxml"));
            baseAnchorPane.getChildren().setAll(pane);
        }catch(IOException | NullPointerException ex){
            System.out.println("Something went wrong.");
            ex.printStackTrace();
        }
    }

    public void loadHomeDetailPane(AnchorPane detailAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/appHome/homeDetailPane.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        }catch(IOException | NullPointerException ex){
            System.out.println("Something went wrong.");
        }
    }


    public void loadUserManagement(AnchorPane detailAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/userManagement/userDetailPane.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println("Something went wrong.");
        }
    }

    public void loadVehicleManagement(AnchorPane detailAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/vehicleManagement/vehicleDetailPane.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println("Something went wrong.");
        }
    }

    public void loadServiceManagement(AnchorPane detailAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/serviceManagement/servicesDetailPane.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println("Something went wrong.");
        }
    }

    public void loadPaymentManagement(AnchorPane detailAnchorPane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/paymentManagement/paymentDetailPane.fxml"));
            detailAnchorPane.getChildren().setAll(pane);
        }catch(IOException ex){
            System.out.println("Something went wrong.");
        }
    }


}
