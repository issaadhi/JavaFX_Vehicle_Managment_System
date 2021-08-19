package controller.vehicleManagement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Vehicle;
import services.VehicleService;
import util.Validation.VehicleDataValidation;
import util.utility.VehicleAlert;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

public class VehicleDetailPaneController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    public Vehicle selectedVehicle = null;

    @FXML
    private TextField tvnumber;

    @FXML
    private TextField tvmodel;

    @FXML
    private TextField tvcolour;

    @FXML
    private TextField tvyear;

    @FXML
    private TextField tvstatus;

    @FXML
    private TableView<Vehicle> tviewinfo;

    @FXML
    private TableColumn<Vehicle, String> colvnumber;

    @FXML
    private TableColumn<Vehicle, String> colvmodel;

    @FXML
    private TableColumn<Vehicle, String> colvcolour;

    @FXML
    private TableColumn<Vehicle, Integer> colvyer;

    @FXML
    private TableColumn<Vehicle, String> colvstatus;

    @FXML
    private Button buinsert;

    @FXML
    private Button budelete;

    @FXML
    private Button buupdate;

    @FXML
    private Label vnumberLabel;

    @FXML
    private Label vmodelLabel;

    @FXML
    private Label vcolorLabel;

    @FXML
    private Label vyearLabel;

    @FXML
    private Label vstatusLabel;

    @FXML
    private ComboBox<String> cbx;

    @FXML
    private TextField searchvehicle;

    @FXML
    private Button vUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showVehicle();
        setvData();
        searchTable();
        clearLabels();

    }

    @FXML
    void deletevehicle(ActionEvent event) {
        if(!selectedVehicle.getVnumber().isEmpty() || selectedVehicle.getVnumber() != null){
            Optional<ButtonType> action = VehicleAlert.deleteConfirmation("Vehicle Information");
            if (action.get() == ButtonType.OK) {
                VehicleService paymentService = new VehicleService();
                if (paymentService.deletevehicle(selectedVehicle)) {
                    VehicleAlert.deleteSuccesfull("Vehicle");
                    clearFields();
                    showVehicle();
                } else
                    VehicleAlert.deleteFailed("Vehicle");

            }
        }else
            VehicleAlert.selectRowToDelete("Vehicle");

    }
    @FXML
    void setvaction(ActionEvent event) {
        if (cbx.getValue().equals("Insert Vehicle")) {
            clearFields();
            buupdate.setVisible(false);
            tvnumber.setEditable(true);
            tvmodel.setEditable(true);
            tvcolour.setEditable(true);
            tvyear.setEditable(true);
            tvstatus.setEditable(true);

        } else if (cbx.getValue().equals("Update Vehicle")) {
            buupdate.setVisible(true);
            tvnumber.setEditable(false);
            tvmodel.setEditable(true);
            tvcolour.setEditable(true);
            tvyear.setEditable(true);
            tvstatus.setEditable(true);
        }

    }

    @FXML
    void insertvehicle(ActionEvent event) {
        clearLabels();
        Vehicle vehicle = new Vehicle();
        if(validateFields()){
        vehicle.setVnumber(tvnumber.getText());
        vehicle.setVmodel(tvmodel.getText());
        vehicle.setVcolour(tvcolour.getText());
        vehicle.setVyear(Integer.parseInt(tvyear.getText()));
        vehicle.setVstatus(tvstatus.getText());

        VehicleService vehicleService = new VehicleService();
        if (vehicleService.insertvehicle(vehicle)){
            VehicleAlert.insertSuccesfull("Vehicle");
            clearFields();
            showVehicle();
            searchTable();
            clearLabels();
        }
        else
            VehicleAlert.insertionFailed("Vehicle");

    }else{
        setValidationMessage();
    }

    }


    @FXML
    void updatevehicle(ActionEvent event) {
        Vehicle vehicle = new Vehicle();
        if (cbx.getValue().equals("Update Vehicle")) {
            if(validateFields()){
            vehicle.setVnumber(tvnumber.getText());
            vehicle.setVmodel(tvmodel.getText());
            vehicle.setVcolour(tvcolour.getText());
            vehicle.setVyear(Integer.parseInt(tvyear.getText()));
            vehicle.setVstatus(tvstatus.getText());
            VehicleService vehicleService = new VehicleService();
            if(vehicleService.updatevehicle(vehicle)){
                VehicleAlert.updateSuccesfull("Vehicle updated Successfully");
                clearFields();
                clearLabels();
                showVehicle();

            }else
                VehicleAlert.updateFailed("Vehicle upadate Failed");

        }else{
            setValidationMessage();
        }

        }

    }

    public void setvData() {
        ObservableList<String> actionList = FXCollections.observableArrayList("Insert Vehicle", "Update Vehicle", "Delete Vehicle");
        cbx.setValue("Insert Vehicle");
        cbx.setItems(actionList);
    }

    //Show details on Table
    public void showVehicle(){
        ObservableList<Vehicle> List;
        List = VehicleService.getVehicleList();

        colvnumber.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vnumber"));
        colvmodel.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vmodel"));
        colvcolour.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vcolour"));
        colvyer.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("vyear"));
        colvstatus.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vstatus"));

        tviewinfo.setItems(List);

    }

    @FXML
    private void clearFields() {
        tvnumber.setText("");
        tvmodel.setText("");
        tvcolour.setText("");
        tvyear.setText("");
        tvstatus.setText("");
        cbx.setValue("Insert Vehicle");
    }

    private void clearLabels(){
        vnumberLabel.setText("");
        vmodelLabel.setText("");
        vcolorLabel.setText("");
        vyearLabel.setText("");
        vstatusLabel.setText("");
    }


    @FXML
    void setSelectedVehicleToFields(MouseEvent event) {

        cbx.setValue("Update Vehicle");
        buupdate.setVisible(true);
        tvmodel.setEditable(false);
        selectedVehicle = tviewinfo.getSelectionModel().getSelectedItem();
        tvnumber.setText(selectedVehicle.getVnumber());
        tvmodel.setText(selectedVehicle.getVmodel());
        tvcolour.setText(selectedVehicle.getVcolour());
        tvyear.setText(String.valueOf(selectedVehicle.getVyear()));
        tvstatus.setText(selectedVehicle.getVstatus());

    }

    public void searchTable(){
        VehicleService vehicleService = new VehicleService();
        SortedList<Vehicle> sortedData = vehicleService.searchTable(searchvehicle);

        sortedData.comparatorProperty().bind(tviewinfo.comparatorProperty());
        tviewinfo.setItems(sortedData);

    }


    private boolean validateFields() {
        if (VehicleDataValidation.TextFieldNotEmpty(tvnumber)
                && VehicleDataValidation.TextFieldNotEmpty(tvmodel)
                && VehicleDataValidation.TextFieldNotEmpty(tvcolour)
                && VehicleDataValidation.TextFieldNotEmpty(tvyear)
                && VehicleDataValidation.TextFieldNotEmpty(tvstatus)


                && VehicleDataValidation.isValidMaximumLength(tvnumber.getText(),10)
                && VehicleDataValidation.isValidMaximumLength(tvmodel.getText(),45)
                && VehicleDataValidation.isValidMaximumLength(tvcolour.getText(),45)
                && VehicleDataValidation.isValidMaximumLength(tvyear.getText(),4)
                && VehicleDataValidation.isValidMaximumLength(tvstatus.getText(),20)


        ) {
            return true;
        } else {
            return false;
        }

    }
    private void setValidationMessage(){
        if(!(VehicleDataValidation.TextFieldNotEmpty(tvnumber)
                && VehicleDataValidation.TextFieldNotEmpty(tvmodel)
                && VehicleDataValidation.TextFieldNotEmpty(tvcolour)
                && VehicleDataValidation.TextFieldNotEmpty(tvyear)
                && VehicleDataValidation.TextFieldNotEmpty(tvstatus))){


            VehicleDataValidation.TextFieldNotEmpty(tvnumber, vnumberLabel,"Vehicle No Cannot be Empty");
            VehicleDataValidation.TextFieldNotEmpty(tvmodel, vmodelLabel,"Vehicle Model Cannot be Empty");
            VehicleDataValidation.TextFieldNotEmpty(tvcolour, vcolorLabel,"Vehicle color Cannot be Empty");
            VehicleDataValidation.TextFieldNotEmpty(tvyear, vyearLabel,"Vehicle year no Cannot be Empty");
            VehicleDataValidation.TextFieldNotEmpty(tvstatus, vstatusLabel,"Vehicle Status Cannot be Empty");

        }
        if(!(VehicleDataValidation.isValidMaximumLength(tvnumber.getText(),10)
                && VehicleDataValidation.isValidMaximumLength(tvmodel.getText(),45)
                && VehicleDataValidation.isValidMaximumLength(tvcolour.getText(),45)
                && VehicleDataValidation.isValidMaximumLength(tvyear.getText(),4)
                && VehicleDataValidation.isValidMaximumLength(tvstatus.getText(),20))){

            VehicleDataValidation.isValidMaximumLength(tvnumber.getText(),10,vnumberLabel,"Vehicle No Length Limit Exceeded");
            VehicleDataValidation.isValidMaximumLength(tvmodel.getText(),45,vmodelLabel,"Vehicle Model Length Limit Exceeded");
            VehicleDataValidation.isValidMaximumLength(tvmodel.getText(),45,vcolorLabel,"Vehicle color Length Limit Exceeded");
            VehicleDataValidation.isValidMaximumLength(tvmodel.getText(),4,vyearLabel,"Vehicle year Limit Exceeded");
            VehicleDataValidation.isValidMaximumLength(tvstatus.getText(),20,vstatusLabel,"Vehicle Status Length Limit Exceeded");

        }

    }


}
