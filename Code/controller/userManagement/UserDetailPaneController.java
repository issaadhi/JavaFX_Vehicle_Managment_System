package controller.userManagement;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import model.User;
import services.UserService;
import util.Validation.UserDataValidation;
import util.utility.UserAlertMsg;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;



public class UserDetailPaneController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    public User selectedUser = null;

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> fNameColumn;

    @FXML
    private TableColumn<User, String> lNameColumn;

    @FXML
    private TableColumn<User, String> uTypeColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> uStatusColumn;

    @FXML
    private TextField emaiTextField;

    @FXML
    private TextField uFNameTextFiled;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label fNameLabel;

    @FXML
    private Label lnameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private ComboBox<String> actionChoiceBox;

    @FXML
    private ComboBox<String> uTypeChoiceBox;

    @FXML
    private ComboBox<String> uStatusChoiceBox;

    @FXML
    private TextField ulNameTextFiled;

    @FXML
    private PasswordField passwordFiled;

    @FXML
    private PasswordField cpasswordFiled;


    @FXML
    private JFXButton updateButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadUserData();
        searchTable();
        clearLabel();
        setData();


    }

    /**
     * set values in combo box
     */
    public void setData() {
        ObservableList<String> actionList = FXCollections.observableArrayList("Add User", "Update User", "Change Password");
        ObservableList<String> typeList = FXCollections.observableArrayList("Admin", "Employee");
        ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Disabled");
        actionChoiceBox.setValue("Add User");
        actionChoiceBox.setItems(actionList);

        uStatusChoiceBox.setValue("Active");
        uStatusChoiceBox.setItems(statusList);

        uTypeChoiceBox.setValue("Admin");
        uTypeChoiceBox.setItems(typeList);


    }

    /**
     * load user details into table
     */

    private void loadUserData() {
        UserService userService = new UserService();
        ObservableList<User> userObservableList = userService.loadAllUser();
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("uEmail"));
        fNameColumn.setCellValueFactory(new PropertyValueFactory<>("uFname"));
        lNameColumn.setCellValueFactory(new PropertyValueFactory<>("uLastName"));
        uTypeColumn.setCellValueFactory(new PropertyValueFactory<>("uType"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("uPassword"));
        uStatusColumn.setCellValueFactory(new PropertyValueFactory<>("uStatus"));

        userTableView.setItems(null);
        userTableView.setItems(userObservableList);
    }


    @FXML
    void addUser(ActionEvent event) {
        clearLabel();
        User user = new User();
        if(validateFields()) {
            user.setuEmail(emaiTextField.getText());
            user.setuFname(uFNameTextFiled.getText());
            user.setuLastName(ulNameTextFiled.getText());
            user.setuType(uTypeChoiceBox.getValue());
            user.setuPassword(passwordFiled.getText());
            user.setuStatus(uStatusChoiceBox.getValue());

            UserService userService = new UserService();
            if (userService.insertUserData(user)) {
                UserAlertMsg.insertSuccesfully("User");
                clearFields();
                loadUserData();
                clearLabel();
                searchTable();
            } else
                UserAlertMsg.insertionFailed("User");

        }else {
            setValidationMessage();
        }


    }


    @FXML
    void updateUser(ActionEvent event) {
        clearLabel();
        User user = new User();
        if (actionChoiceBox.getValue().equals("Update User")) {
            if(validateFields()) {
                user.setuEmail(emaiTextField.getText());
                user.setuFname(uFNameTextFiled.getText());
                user.setuLastName(ulNameTextFiled.getText());
                user.setuType(uTypeChoiceBox.getValue());
                user.setuPassword(passwordFiled.getText());
                user.setuStatus(uStatusChoiceBox.getValue());

                UserService userService = new UserService();
                if (userService.updatetUserData(user)) {
                    UserAlertMsg.updateSuccesfully("User");
                    clearFields();
                    loadUserData();
                    clearLabel();
                } else
                    UserAlertMsg.updateFailed("User");

            }else{
                setValidationMessage();
            }

        }else {
            if(validateFields()){
                user.setuEmail(emaiTextField.getText());
                user.setuPassword(passwordFiled.getText());

                UserService userService = new UserService();
                if(userService.updateUserPassword(user)){
                    UserAlertMsg.updateSuccesfully("Password Updated Successfully");
                    clearFields();
                    loadUserData();
                    clearLabel();
                }else
                    UserAlertMsg.insertionFailed("Failed to Update Password");

            }else{
                setValidationMessage();
            }

        }
    }


    @FXML
    void deleteUser(ActionEvent event) {
        if (!selectedUser.getuEmail().isEmpty() || selectedUser.getuEmail() != null) {
            Optional<ButtonType> action = UserAlertMsg.deleteConfirmation("User Info");
            if (action.get() == ButtonType.OK) {
                UserService userService = new UserService();
                if (userService.deleteUser(selectedUser)) {
                    UserAlertMsg.deleteSuccesfull("User");
                    clearFields();
                    loadUserData();
                    clearLabel();
                } else
                    UserAlertMsg.deleteFailed("User");
            }
        } else
            UserAlertMsg.selectRowToDelete("User");

    }


    public void searchTable() {
        UserService userServices = new UserService();
        //Retrieving sorted data from Controller
        SortedList<User> sortedData = userServices.searchTable(searchTextField);

        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(userTableView.comparatorProperty());
        //adding sorted and filtered data to the table
        userTableView.setItems(sortedData);

    }

    private void clearFields() {
        emaiTextField.setText("");
        uFNameTextFiled.setText("");
        ulNameTextFiled.setText("");
        passwordFiled.setText("");
        cpasswordFiled.setText("");
        actionChoiceBox.setValue("Add User");
        uTypeChoiceBox.setValue("Admin");
        uStatusChoiceBox.setValue("Active");

    }

    @FXML
    void clearFields(ActionEvent event) {
        clearFields();

    }

    private void clearLabel() {
        emailLabel.setText("");
        fNameLabel.setText("");
        lnameLabel.setText("");
        passwordLabel.setText("");


    }


    /**
     * This method use to set values in a selected row into to text fields
     *
     * @param event
     */

    @FXML
    void setSelectedUserToFields(MouseEvent event) {

        actionChoiceBox.setValue("Update User");
        updateButton.setVisible(true);
        emaiTextField.setEditable(false);
        selectedUser = userTableView.getSelectionModel().getSelectedItem();
        emaiTextField.setText(selectedUser.getuEmail());
        uFNameTextFiled.setText(selectedUser.getuFname());
        ulNameTextFiled.setText(selectedUser.getuLastName());
        uTypeChoiceBox.setValue(selectedUser.getuType());
        uStatusChoiceBox.setValue(selectedUser.getuStatus());


    }


    /**
     * Update button visible and invisible
     */
    @FXML
    private void setUserAction() {
        if (actionChoiceBox.getValue().equals("Add User")) {
            clearFields();
            updateButton.setVisible(false);
            emaiTextField.setEditable(true);
            uFNameTextFiled.setEditable(true);
            ulNameTextFiled.setEditable(true);
            uTypeChoiceBox.setDisable(false);
            uStatusChoiceBox.setDisable(false);
        } else if (actionChoiceBox.getValue().equals("Update User")) {
            updateButton.setVisible(true);
            emaiTextField.setEditable(false);
            uFNameTextFiled.setEditable(true);
            ulNameTextFiled.setEditable(true);
            uTypeChoiceBox.setDisable(false);
            uStatusChoiceBox.setDisable(false);
        } else {
            updateButton.setVisible(true);
            emaiTextField.setEditable(false);
            uFNameTextFiled.setEditable(false);
            ulNameTextFiled.setEditable(false);
            uTypeChoiceBox.setDisable(true);
            uStatusChoiceBox.setDisable(true);
        }

    }

    @FXML
    private void checkEmailAvailability() {
        ObservableList<User> modelList = userTableView.getItems();
        ArrayList<String> userList = new ArrayList<>();
        for (User user : modelList) {
            userList.add(user.getuEmail().toLowerCase());
        }
        if (emaiTextField.getText().isEmpty()) {
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("User Name Cannot be empty");
        } else if (userList.contains(emaiTextField.getText().toLowerCase())) {
            emailLabel.setStyle("-fx-text-fill: #ff0000 ");
            emailLabel.setText("User Name Already Taken!!");
        } else {
            emailLabel.setStyle("-fx-text-fill: #00B605 ");
            emailLabel.setText("User Name Available");
        }
    }

    private boolean validateFields() {
        if (UserDataValidation.TextFieldNotEmpty(emaiTextField)
                && UserDataValidation.TextFieldNotEmpty(uFNameTextFiled)
                && UserDataValidation.TextFieldNotEmpty(ulNameTextFiled)
                && UserDataValidation.TextFieldNotEmpty(uTypeChoiceBox.getValue())
                && UserDataValidation.PasswordFieldNotEmpty(passwordFiled)
                && UserDataValidation.PasswordFieldNotEmpty(cpasswordFiled)
                && UserDataValidation.TextFieldNotEmpty(uStatusChoiceBox.getValue())

                && UserDataValidation.isValidMaximumLength(emaiTextField.getText(),45)
                && UserDataValidation.isValidMaximumLength(uFNameTextFiled.getText(),45)
                && UserDataValidation.isValidMaximumLength(ulNameTextFiled.getText(),45)
                && UserDataValidation.isValidMaximumLength(passwordFiled.getText(),200)
                && UserDataValidation.isValidMaximumLength(cpasswordFiled.getText(),200)

                && UserDataValidation.isValidEmail(emaiTextField.getText())
                && UserDataValidation.PasswordFieldMatch(passwordFiled, cpasswordFiled)



        ) {
            return true;
        } else {
            return false;
        }

    }

    private void setValidationMessage(){
        if(!(UserDataValidation.TextFieldNotEmpty(emaiTextField)
                && UserDataValidation.TextFieldNotEmpty(uFNameTextFiled)
                && UserDataValidation.TextFieldNotEmpty(ulNameTextFiled)
                && UserDataValidation.TextFieldNotEmpty(uTypeChoiceBox.getValue())
                && UserDataValidation.PasswordFieldNotEmpty(passwordFiled)
                && UserDataValidation.PasswordFieldNotEmpty(cpasswordFiled)
                && UserDataValidation.TextFieldNotEmpty(uStatusChoiceBox.getValue()))){

            UserDataValidation.TextFieldNotEmpty(emaiTextField, emailLabel,"Email Cannot be Empty");
            UserDataValidation.TextFieldNotEmpty(uFNameTextFiled, fNameLabel,"First name Cannot be Empty");
            UserDataValidation.TextFieldNotEmpty(ulNameTextFiled, lnameLabel,"Last name Cannot be Empty");
            UserDataValidation.TextFieldNotEmpty(passwordFiled, passwordLabel,"Password Cannot be Empty");
            UserDataValidation.TextFieldNotEmpty(cpasswordFiled, passwordLabel,"Confirm Password Cannot be Empty");

        }

        if(!(UserDataValidation.isValidMaximumLength(emaiTextField.getText(),45)
                && UserDataValidation.isValidMaximumLength(uFNameTextFiled.getText(),45)
                && UserDataValidation.isValidMaximumLength(ulNameTextFiled.getText(),45)
                && UserDataValidation.isValidMaximumLength(passwordFiled.getText(),200)
                && UserDataValidation.isValidMaximumLength(cpasswordFiled.getText(),200))){

            UserDataValidation.isValidMaximumLength(emaiTextField.getText(),45,emailLabel,"Email Length Limit Exceeded");
            UserDataValidation.isValidMaximumLength(uFNameTextFiled.getText(),45,fNameLabel,"First Name Length Limit Exceeded");
            UserDataValidation.isValidMaximumLength(ulNameTextFiled.getText(),45,lnameLabel,"Last Name Length Limit Exceeded");
            UserDataValidation.isValidMaximumLength(passwordFiled.getText(),45,passwordLabel,"Password Length Limit Exceeded");
            UserDataValidation.isValidMaximumLength(cpasswordFiled.getText(),45,passwordLabel,"Password Length Limit Exceeded");

        }

        if(!(UserDataValidation.isValidEmail(emaiTextField.getText())
                && UserDataValidation.PasswordFieldMatch(passwordFiled, cpasswordFiled))){

            UserDataValidation.isValidEmail(emaiTextField.getText(),emailLabel,"Invalid Email");
            UserDataValidation.PasswordFieldMatch(passwordFiled, cpasswordFiled, passwordLabel, passwordLabel,"Password not match");

        }




    }



}