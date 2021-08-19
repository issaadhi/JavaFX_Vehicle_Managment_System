package services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.Payment;
import util.dbConnection.DBConnection;
import util.query.PaymentQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;


public class PaymentService {
    static Connection conn;

    public PaymentService() {
        conn = DBConnection.getDBConnection();
    }


    public static ObservableList<Payment> getPaymentList() {
        ObservableList<Payment> paymentlist = FXCollections.observableArrayList();
        conn = DBConnection.getDBConnection();
        String query1 = PaymentQuery.load_All_Payment_Data;
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query1);
            Payment payment;
            while (rs.next()) {
                payment = new Payment(rs.getString("Payment_ID"), rs.getString("Vehicle_No"), rs.getString("Customer_Name"), rs.getString("Customer_ID"), rs.getString("Customer_TP"), rs.getString("Amount"));
                paymentlist.add(payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentlist;


    }






    public boolean insertPaymentData(Payment payment) {
        boolean resultValue = false;

        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(PaymentQuery.insert_Payment_Data);
            pst.setString(1, payment.getPayment_ID());
            pst.setString(2, payment.getVehicle_No());
            pst.setString(3, payment.getCustomer_Name());
            pst.setString(4, payment.getCustomer_ID());
            pst.setString(5, payment.getCustomer_TP());
            pst.setString(6, payment.getAmount());
            pst.executeUpdate();
            resultValue = true;

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return resultValue;
    }



    public boolean updatePayment(Payment payment) {
        boolean resultValue = false;

        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(PaymentQuery.update_Payment_Data);


            pst.setString(1, payment.getVehicle_No());
            pst.setString(2, payment.getCustomer_Name());
            pst.setString(3, payment.getCustomer_ID());
            pst.setString(4, payment.getCustomer_TP());
            pst.setString(5, payment.getAmount());
            pst.setString(6, payment.getPayment_ID());
            pst.execute();
            resultValue = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;

    }





    public boolean deletePayment(Payment payment){
        boolean resultValue = false;
        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(PaymentQuery.delete_Payment_Data);
            pst.setString(1, payment.getPayment_ID());
            pst.executeUpdate();
            resultValue = true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return resultValue;
    }




    public SortedList<Payment> searchTable(TextField searchTextField){

        ObservableList<Payment> paymentData = getPaymentList();


        FilteredList<Payment> filteredData = new FilteredList<>(paymentData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(payment -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(payment.getPayment_ID().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(payment.getVehicle_No().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(payment.getCustomer_Name().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(payment.getCustomer_ID().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(payment.getCustomer_TP().toLowerCase().indexOf(lowerCaseFilter) !=-1){

                    return true;
                }else{

                    return false;
                }
            });
        });

        SortedList<Payment> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }

}
