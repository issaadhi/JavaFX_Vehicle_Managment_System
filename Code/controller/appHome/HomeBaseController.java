package controller.appHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.authenticate.Authentication;
import util.navigation.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeBaseController implements Initializable {
    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private AnchorPane detailAnchorPane;

    @FXML
    private Label userNameLabel;

    private Navigation navigation = new Navigation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadHomeDetailPane();
        setData();
    }

    @FXML
    private void logout(ActionEvent actionEvent){
        navigation.logout(baseAnchorPane);
    }


    private void loadHomeDetailPane(){
        navigation.loadHomeDetailPane(detailAnchorPane);
    }

    @FXML
    private void loadHomeDetailPane(ActionEvent actionEvent){
        navigation.loadHomeDetailPane(detailAnchorPane);
    }

    @FXML
    private void loadUserManagement(ActionEvent actionEvent){
        navigation.loadUserManagement(detailAnchorPane);
    }

    @FXML
    private void loadVehicleManagement(ActionEvent actionEvent){
        navigation.loadVehicleManagement(detailAnchorPane);
    }

    @FXML
    private void loadServiceManagement(ActionEvent actionEvent){
        navigation.loadServiceManagement(detailAnchorPane);
    }

    @FXML
    private void loadPaymentManagement(ActionEvent actionEvent){
        navigation.loadPaymentManagement(detailAnchorPane);
    }



    private void setData(){
        userNameLabel.setText(Authentication.getAuthenticatedUser().getuFname() + " " +Authentication.getAuthenticatedUser().getuLastName());
    }
}
