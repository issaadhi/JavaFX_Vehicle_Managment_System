package services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.VMService;
import util.dbConnection.DBConnection;
import util.query.VMServiceQuery;

import java.sql.*;

public class MaintainService {

    static Connection conn;
    public MaintainService(){
        conn = DBConnection.getDBConnection();
    }

    public static ObservableList<VMService> getMaintenanceList() {
        ObservableList<VMService> servicelist = FXCollections.observableArrayList();
        conn = DBConnection.getDBConnection();
        String query1 = VMServiceQuery.load_All_VehicleService_Data;
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query1);
            VMService VMService;
            while (rs.next()) {
                VMService = new VMService(rs.getString("Service_id"),rs.getString("Vehicle_id"), rs.getString("Service_description"), rs.getString("Service_amount"), rs.getString("Service_date"));
                servicelist.add(VMService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return servicelist;


    }


    public boolean insertMaintenanceData(VMService vmService) {
        boolean resultValue = false;

        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(VMServiceQuery.insert_VehicleService_Data);
            pst.setString(1, vmService.getService_id());
            pst.setString(2, vmService.getVehicle_id());
            pst.setString(3, vmService.getService_description());
            pst.setString(4, vmService.getService_amount());
            pst.setString(5, vmService.getService_date());
            pst.executeUpdate();
            resultValue = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;

    }



    public boolean updateMaintenance(VMService vmService) {
        boolean resultValue = false;

        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(VMServiceQuery.update_Maintenance_Data);
            pst.setString(1, vmService.getVehicle_id());
            pst.setString(2, vmService.getService_description());
            pst.setString(3, vmService.getService_amount());
            pst.setString(4, vmService.getService_date());
            pst.setString(5, vmService.getService_id());
            pst.execute();
            resultValue = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;

    }



    public boolean deleteMaintenance(VMService vmService){
        boolean resultValue = false;
        try {
            PreparedStatement pst;
            pst = conn.prepareStatement(VMServiceQuery.delete_Maintenance_Data);
            pst.setString(1, vmService.getService_id());
            pst.executeUpdate();
            resultValue = true;
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return resultValue;
    }




    public SortedList<VMService> searchTable(TextField searchTextField){

        ObservableList<VMService> vmService = getMaintenanceList();


        FilteredList<VMService> filteredData = new FilteredList<>(vmService, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(VMService -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(VMService.getService_id().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(VMService.getService_description().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(VMService.getVehicle_id().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(VMService.getService_date().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else{

                    return false;
                }
            });
        });

        SortedList<VMService> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }




}
