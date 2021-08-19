package controller.userManagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;
import util.authenticate.Authentication;
import util.navigation.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane baseAnchorPane;

    @FXML
    private ImageView img;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField pwPasswordField;

    @FXML
    private Label validationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void loginToSystem(ActionEvent event) {

        User user = new User();
        user.setuEmail(userNameTextField.getText());
        user.setuPassword(pwPasswordField.getText());

        String validationMessage = Authentication.authenticateUser(user);
        validationLabel.setText("");
        if(validationMessage.equals("success")){
            Navigation navigation = new Navigation();
            navigation.loadAppHome(baseAnchorPane);
            System.out.println("Success");
        }else{
            validationLabel.setText(validationMessage);
        }
    }

    @FXML
    void clearFields(ActionEvent event) {
        userNameTextField.setText("");
        pwPasswordField.setText("");


    }


    @FXML
    private void clearLabel(MouseEvent event){
        validationLabel.setText("");
    }
}
