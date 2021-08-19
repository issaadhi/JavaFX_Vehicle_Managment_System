package controller.paymentManagement;

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

import model.Payment;
import services.PaymentService;
import util.Validation.PaymentDataValidation;
import util.utility.PaymentAlertMsg;


import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentDetailPaneController implements Initializable {

    @FXML
    private AnchorPane detailAnchorPane;

    public Payment selectedPayment = null;

    @FXML
    private TableView<Payment> tbview;

    @FXML
    private TableColumn<Payment, String> tbPaymentID;

    @FXML
    private TableColumn<Payment, String> tbVehicleNo;

    @FXML
    private TableColumn<Payment, String> tbCustomerName;

    @FXML
    private TableColumn<Payment, String> tbID;

    @FXML
    private TableColumn<Payment, String> tbTP;

    @FXML
    private TableColumn<Payment, Float> tbAmount;

    @FXML
    private TextField tfPaymentID;

    @FXML
    private TextField tfVehicleNo;

    @FXML
    private TextField tfCustomerName;

    @FXML
    private TextField tfCustomerID;

    @FXML
    private TextField tfCustomerTP;

    @FXML
    private TextField tfAmount;


    @FXML
    private Label paymentLabel;

    @FXML
    private Label tpLabel;

    @FXML
    private Label vehicleLabel;

    @FXML
    private Label cusNameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label cusIDLabel;


    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private TextField SearchBox;

    @FXML
    private JFXButton bModify;

    public void initialize(URL url, ResourceBundle rb) {

        showPayment();
        searchTable();
        clearLabels();
        setData();
    }


    /**View on table**/
    public void showPayment() {
        ObservableList<Payment> list;
        list = PaymentService.getPaymentList();

        tbPaymentID.setCellValueFactory(new PropertyValueFactory<Payment,String>("Payment_ID"));
        tbVehicleNo.setCellValueFactory(new PropertyValueFactory<Payment, String>("Vehicle_No"));
        tbCustomerName.setCellValueFactory(new PropertyValueFactory<Payment, String>("Customer_Name"));
        tbID.setCellValueFactory(new PropertyValueFactory<Payment, String>("Customer_ID"));
        tbTP.setCellValueFactory(new PropertyValueFactory<Payment, String>("Customer_TP"));
        tbAmount.setCellValueFactory(new PropertyValueFactory<Payment, Float>("Amount"));

        tbview.setItems(list);
    }


    /** ComboBox **/
    public void setData (){
        ObservableList<String> actionList = FXCollections.observableArrayList("Add Payment","Update Payment","Delete Payment");
        ComboBox.setValue("Add Payment");
        ComboBox.setItems(actionList);

}
    @FXML
    void addPayment(ActionEvent event){
        clearLabels();
        Payment payment = new Payment();
        if(validateFields()){
        payment.setPayment_ID(tfPaymentID.getText());
        payment.setVehicle_No(tfVehicleNo.getText());
        payment.setCustomer_Name(tfCustomerName.getText());
        payment.setCustomer_ID(tfCustomerID.getText());
        payment.setCustomer_TP(tfCustomerTP.getText());
        payment.setAmount(tfAmount.getText());

        PaymentService paymentService = new PaymentService();
        if (paymentService.insertPaymentData(payment)){
            PaymentAlertMsg.insertSuccesfully("Payment");
            clearFields();
            clearLabels();
            showPayment();
            searchTable();
        } else
            PaymentAlertMsg.insertionFailed("Payment");

    }else{
        setValidationMessage();
    }

    }

    @FXML
    public void searchTable(){
        PaymentService paymentService = new PaymentService();
        SortedList<Payment> sortedData = paymentService.searchTable(SearchBox);

        sortedData.comparatorProperty().bind(tbview.comparatorProperty());
        tbview.setItems(sortedData);

    }

    @FXML
    private void clearFields() {

        paymentLabel.setText("");
        tfPaymentID.setText("");
        tfVehicleNo.setText("");
        tfCustomerName.setText("");
        tfCustomerID.setText("");
        tfCustomerTP.setText("");
        tfAmount.setText("");
        ComboBox.setValue("Add Payment");
    }

    private void clearLabels(){
        paymentLabel.setText("");
        cusIDLabel.setText("");
        cusNameLabel.setText("");
        tpLabel.setText("");
        amountLabel.setText("");
        vehicleLabel.setText("");
    }



    @FXML
    void updatePayment (ActionEvent event) {
        Payment payment = new Payment();
        if (ComboBox.getValue().equals("Update Payment")) {
            if(validateFields()){
            payment.setPayment_ID(tfPaymentID.getText());
            payment.setVehicle_No(tfVehicleNo.getText());
            payment.setCustomer_Name(tfCustomerName.getText());
            payment.setCustomer_ID(tfCustomerID.getText());
            payment.setCustomer_TP(tfCustomerTP.getText());
            payment.setAmount(tfAmount.getText());
            PaymentService paymentService = new PaymentService();
            if(paymentService.updatePayment(payment)){
                PaymentAlertMsg.updateSuccesfully("Payment");
                clearFields();
                clearLabels();
                showPayment();
            }else
                PaymentAlertMsg.updateFailed("Payment");

        }else{
            setValidationMessage();
        }
        }
    }

    @FXML
    void deletePayment (ActionEvent event) {
        if(!selectedPayment.getPayment_ID().isEmpty() || selectedPayment.getPayment_ID() != null) {
            Optional<ButtonType> action = PaymentAlertMsg.deleteConfirmation("Payment Info");
            if (action.get() == ButtonType.OK) {
                PaymentService paymentService = new PaymentService();
                if (paymentService.deletePayment(selectedPayment)) {
                    PaymentAlertMsg.deleteSuccesfull("Payment");
                    clearFields();
                    showPayment();
                    clearLabels();
                } else
                    PaymentAlertMsg.deleteFailed("Payment");
            }
        }else
                PaymentAlertMsg.selectRowToDelete("Payment");


    }


    @FXML
    void setSelectedPaymentToFields(MouseEvent event) {

        ComboBox.setValue("Update Payment");
        bModify.setVisible(true);
        tfPaymentID.setEditable(false);
        selectedPayment = tbview.getSelectionModel().getSelectedItem();
        tfPaymentID.setText(selectedPayment.getPayment_ID());
        tfVehicleNo.setText(selectedPayment.getVehicle_No());
        tfCustomerName.setText(selectedPayment.getCustomer_Name());
        tfCustomerID.setText(selectedPayment.getCustomer_ID());
        tfCustomerTP.setText(selectedPayment.getCustomer_TP());
        tfAmount.setText(selectedPayment.getAmount());

    }



    @FXML
    void setPaymentAction(ActionEvent event) {
        if (ComboBox.getValue().equals("Add Payment")) {
            clearFields();
            bModify.setVisible(false);
            tfPaymentID.setEditable(true);
            tfVehicleNo.setEditable(true);
            tfCustomerName.setEditable(true);
            tfCustomerID.setEditable(true);
            tfCustomerTP.setEditable(true);
            tfAmount.setEditable(true);
        } else if (ComboBox.getValue().equals("Update Payment")) {
            bModify.setVisible(true);
            tfPaymentID.setEditable(false);
            tfVehicleNo.setEditable(true);
            tfCustomerName.setEditable(true);
            tfCustomerID.setEditable(true);
            tfCustomerTP.setEditable(true);
            tfAmount.setEditable(true);
        }
    }

    @FXML
    private void checkPaymentAvailability() {
        ObservableList<Payment> modelList = tbview.getItems();
        ArrayList<String> userList = new ArrayList<>();
        for (Payment payment : modelList) {
            userList.add(payment.getPayment_ID().toLowerCase());
        }
        if (paymentLabel.getText().isEmpty()) {
            paymentLabel.setStyle("-fx-text-fill: #ff0000 ");
            paymentLabel.setText("Payment ID Cannot be empty");
        } else if (userList.contains(paymentLabel.getText().toLowerCase())) {
            paymentLabel.setStyle("-fx-text-fill: #ff0000 ");
            paymentLabel.setText("Payment ID Already Taken!!");
        } else {
            paymentLabel.setStyle("-fx-text-fill: #00B605 ");
            paymentLabel.setText("Payment ID Available");
        }
    }

    private boolean validateFields() {
        if (PaymentDataValidation.TextFieldNotEmpty(tfPaymentID)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerID)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerName)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerTP)
                && PaymentDataValidation.TextFieldNotEmpty(tfAmount)
                && PaymentDataValidation.TextFieldNotEmpty(tfVehicleNo)


                && PaymentDataValidation.isValidMaximumLength(tfPaymentID.getText(),11)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerID.getText(),12)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerName.getText(),50)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerTP.getText(),10)
                && PaymentDataValidation.isValidMaximumLength(tfVehicleNo.getText(),10)


                && PaymentDataValidation.isValidPhoneNo(tfCustomerTP.getText())

        ) {
            return true;
        } else {
            return false;
        }

    }
    private void setValidationMessage(){
        if(!(PaymentDataValidation.TextFieldNotEmpty(tfPaymentID)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerID)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerName)
                && PaymentDataValidation.TextFieldNotEmpty(tfCustomerTP)
                && PaymentDataValidation.TextFieldNotEmpty(tfAmount)
                && PaymentDataValidation.TextFieldNotEmpty(tfVehicleNo))){


        PaymentDataValidation.TextFieldNotEmpty(tfPaymentID, paymentLabel,"Payment ID Cannot be Empty");
        PaymentDataValidation.TextFieldNotEmpty(tfCustomerID, cusIDLabel,"Customer ID Cannot be Empty");
        PaymentDataValidation.TextFieldNotEmpty(tfCustomerName, cusNameLabel,"Customer Name Cannot be Empty");
        PaymentDataValidation.TextFieldNotEmpty(tfCustomerTP, tpLabel,"Telephone no Cannot be Empty");
        PaymentDataValidation.TextFieldNotEmpty(tfAmount, amountLabel,"Amount Cannot be Empty");
        PaymentDataValidation.TextFieldNotEmpty(tfVehicleNo, vehicleLabel,"Vehicle no Cannot be Empty");


        }
        if(!(PaymentDataValidation.isValidMaximumLength(tfPaymentID.getText(),11)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerID.getText(),12)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerName.getText(),50)
                && PaymentDataValidation.isValidMaximumLength(tfCustomerTP.getText(),10)
                && PaymentDataValidation.isValidMaximumLength(tfVehicleNo.getText(),10))){

            PaymentDataValidation.isValidMaximumLength(tfPaymentID.getText(),11,paymentLabel,"Payment ID Length Limit Exceeded");
            PaymentDataValidation.isValidMaximumLength(tfCustomerID.getText(),12,cusIDLabel,"Customer ID Length Limit Exceeded");
            PaymentDataValidation.isValidMaximumLength(tfCustomerName.getText(),50,cusNameLabel,"Customer Name Length Limit Exceeded");
            PaymentDataValidation.isValidMaximumLength(tfCustomerTP.getText(),10,tpLabel,"Telephone no Limit Exceeded");
            PaymentDataValidation.isValidMaximumLength(tfVehicleNo.getText(),10,vehicleLabel,"Vehicle no Length Limit Exceeded");

    }

        if(!(PaymentDataValidation.isValidPhoneNo(tfCustomerTP.getText())))

    {
        PaymentDataValidation.isValidPhoneNo(tfCustomerTP.getText(), tpLabel, "Invalid Phone No");

    }
}



}

