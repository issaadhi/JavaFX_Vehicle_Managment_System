package controller.serviceManagement;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.VMService;
import services.MaintainService;
import util.Validation.ServiceDataValidation;
import util.utility.ServiceAlertMsg;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceDetailPaneController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    public VMService selectedVMService = null;

    @FXML
    private TextField tfServiceAmount;

    @FXML
    private TableView<VMService> tvVehicleService;

    @FXML
    private TableColumn<VMService, String> colServiceID;

    @FXML
    private TableColumn<VMService, String> colVehicleID;

    @FXML
    private TableColumn<VMService, String> colServiceDescription;

    @FXML
    private TableColumn<VMService, String> colServiceAmount;

    @FXML
    private TableColumn<VMService, String> colServiceDate;

    @FXML
    private TextField tfSearchService;

    @FXML
    private TextField tfServiceID;

    @FXML
    private TextField tfVehicleID;

    @FXML
    private TextField tfServiceDescription;

    @FXML
    private TextField tfServiceDate;

    @FXML
    private Label sIDLabel;

    @FXML
    private Label vNumberLabel;

    @FXML
    private Label sDescLabel;

    @FXML
    private Label sAmountLabel;

    @FXML
    private Label sDateLabel;


    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnDELETE;

    @FXML
    private JFXButton btnCLEAR;

    @FXML
    private JFXButton btnUPDATE;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showServices();
        searchTable();
        clearLabels();



    }

    public void showServices(){
        ObservableList<VMService> list;
        list = MaintainService.getMaintenanceList();

        colServiceID.setCellValueFactory(new PropertyValueFactory<VMService, String>("Service_id"));
        colVehicleID.setCellValueFactory(new PropertyValueFactory<VMService, String>("Vehicle_id"));
        colServiceDescription.setCellValueFactory(new PropertyValueFactory<VMService, String>("Service_description"));
        colServiceAmount.setCellValueFactory(new PropertyValueFactory<VMService, String>("Service_amount"));
        colServiceDate.setCellValueFactory(new PropertyValueFactory<VMService, String>("Service_date"));


        tvVehicleService.setItems(list);

    }

    @FXML
    void addService(ActionEvent event) {

        VMService vmService = new VMService();
        if(validateFields()){
        vmService.setService_id(tfServiceID.getText());
        vmService.setVehicle_id(tfVehicleID.getText());
        vmService.setService_description(tfServiceDescription.getText());
        vmService.setService_amount(tfServiceAmount.getText());
        vmService.setService_date(tfServiceDate.getText());
        MaintainService maintainService = new MaintainService();
        if (maintainService.insertMaintenanceData(vmService)){
            ServiceAlertMsg.insertSuccesfully("Service");
            clearFields();
            showServices();
            clearLabels();
        }
        else
            ServiceAlertMsg.insertionFailed("Service");
    }else{
        setValidationMessage();
    }

    }

    @FXML
    void clearService(ActionEvent event) {
        tfServiceID.setText("");
        tfVehicleID.setText("");
        tfServiceDescription.setText("");
        tfServiceAmount.setText("");
        tfServiceDate.setText("");




    }

    @FXML
    void deleteService(ActionEvent event) {
        if(!selectedVMService.getService_id().isEmpty() || selectedVMService.getService_id() != null) {
            Optional<ButtonType> action = ServiceAlertMsg.deleteConfirmation("Service Info");
            if (action.get() == ButtonType.OK) {
                MaintainService maintainService = new MaintainService();
                if (maintainService.deleteMaintenance(selectedVMService)) {
                    ServiceAlertMsg.deleteSuccesfull("Service");
                    clearFields();
                    showServices();
                } else
                    ServiceAlertMsg.deleteFailed("Service");
            }
        }else
            ServiceAlertMsg.selectRowToDelete("Service");
        }


    @FXML
    void updateService(ActionEvent event) {

        VMService vmservice = new VMService();
        if(validateFields()){
        vmservice.setService_id(tfServiceID.getText());
        vmservice.setVehicle_id(tfVehicleID.getText());
        vmservice.setService_description(tfServiceDescription.getText());
        vmservice.setService_amount(tfServiceAmount.getText());
        vmservice.setService_date(tfServiceDate.getText());

        MaintainService maintainService = new MaintainService();
        if(maintainService.updateMaintenance(vmservice)){

            ServiceAlertMsg.updateSuccesfully("Service");
            clearFields();
            showServices();
            clearLabels();
        }else
            ServiceAlertMsg.updateFailed("Service");


        }else{
            setValidationMessage();
        }

    }

    @FXML
    private void searchTable() {
        MaintainService maintainService = new MaintainService();
        SortedList<VMService> sortedData = maintainService.searchTable(tfSearchService);

        sortedData.comparatorProperty().bind(tvVehicleService.comparatorProperty ());
        tvVehicleService.setItems(sortedData);
    }

    @FXML
    private void clearFields(){
        tfServiceID.setText("");
        tfVehicleID.setText("");
        tfServiceDescription.setText("");
        tfServiceAmount.setText("");
        tfServiceDate.setText("");
    }

    private void clearLabels(){
        sIDLabel.setText("");
        vNumberLabel.setText("");
        sDescLabel.setText("");
        sAmountLabel.setText("");
        sDateLabel.setText("");
    }

    @FXML
    void setSelectedVMServiceToFields(MouseEvent event) {

        //tfServiceID.setEditable(false);
        selectedVMService = tvVehicleService.getSelectionModel().getSelectedItem();
        tfServiceID.setText(selectedVMService.getService_id());
        tfVehicleID.setText(selectedVMService.getVehicle_id());
        tfServiceDescription.setText(selectedVMService.getService_description());
        tfServiceAmount.setText(selectedVMService.getService_amount());
        tfServiceDate.setText(selectedVMService.getService_date());

    }

    private boolean validateFields() {
        if (ServiceDataValidation.TextFieldNotEmpty(tfServiceID)
                && ServiceDataValidation.TextFieldNotEmpty(tfVehicleID)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceDescription)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceDate)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceAmount)


                && ServiceDataValidation.isValidMaximumLength(tfServiceID.getText(),10)
                && ServiceDataValidation.isValidMaximumLength(tfVehicleID.getText(),45)
                && ServiceDataValidation.isValidMaximumLength(tfServiceDescription.getText(),100)
                && ServiceDataValidation.isValidMaximumLength(tfServiceDate.getText(),45)
                && ServiceDataValidation.isValidMaximumLength(tfServiceAmount.getText(),45)


        ) {
            return true;
        } else {
            return false;
        }

    }
    private void setValidationMessage(){
        if(!(ServiceDataValidation.TextFieldNotEmpty(tfServiceID)
                && ServiceDataValidation.TextFieldNotEmpty(tfVehicleID)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceDescription)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceDate)
                && ServiceDataValidation.TextFieldNotEmpty(tfServiceAmount))){


            ServiceDataValidation.TextFieldNotEmpty(tfServiceID, sIDLabel,"Service ID Cannot be Empty");
            ServiceDataValidation.TextFieldNotEmpty(tfVehicleID, vNumberLabel,"Vehicle No Cannot be Empty");
            ServiceDataValidation.TextFieldNotEmpty(tfServiceDescription, sDescLabel,"Description Cannot be Empty");
            ServiceDataValidation.TextFieldNotEmpty(tfServiceDate, sAmountLabel,"Date Cannot be Empty");
            ServiceDataValidation.TextFieldNotEmpty(tfServiceAmount, sDateLabel,"Amount Cannot be Empty");

        }
        if(!(ServiceDataValidation.isValidMaximumLength(tfServiceID.getText(),10)
                && ServiceDataValidation.isValidMaximumLength(tfVehicleID.getText(),45)
                && ServiceDataValidation.isValidMaximumLength(tfServiceDescription.getText(),100)
                && ServiceDataValidation.isValidMaximumLength(tfServiceDate.getText(),45)
                && ServiceDataValidation.isValidMaximumLength(tfServiceAmount.getText(),45))){

            ServiceDataValidation.isValidMaximumLength(tfServiceID.getText(),10,sIDLabel,"Service ID Length Limit Exceeded");
            ServiceDataValidation.isValidMaximumLength(tfVehicleID.getText(),45,vNumberLabel,"Vehicle No Length Limit Exceeded");
            ServiceDataValidation.isValidMaximumLength(tfServiceDescription.getText(),100,sDescLabel,"Description Length Limit Exceeded");
            ServiceDataValidation.isValidMaximumLength(tfServiceDate.getText(),45,sAmountLabel,"Amount Limit Exceeded");
            ServiceDataValidation.isValidMaximumLength(tfServiceAmount.getText(),45,sDateLabel,"Date Length Limit Exceeded");

        }

    }

}
