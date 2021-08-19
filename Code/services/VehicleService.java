package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.Vehicle;
import util.dbConnection.DBConnection;
import util.query.VehicleQuery;

import java.sql.*;

public class VehicleService {
        static Connection connection;

        public VehicleService(){
            connection = DBConnection.getDBConnection();
        }

    public static ObservableList<Vehicle> getVehicleList() {
        ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList();
        connection = DBConnection.getDBConnection();
        String query1 = VehicleQuery.load_All_Vehicle_Data;
        Statement st;
        ResultSet rs;


        try {
            st = connection.createStatement();
            rs = st.executeQuery(query1);
            Vehicle vehicle;
            while(rs.next()) {
                vehicle = new Vehicle(rs.getString("vnumber"),rs.getString("vmodel"), rs.getString("vcolour"), rs.getInt("vyear"), rs.getString("vstatus"));
                vehicleList.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicleList;
    }


    public boolean insertvehicle(Vehicle vehicle) {
        boolean resultValue = false;

        try {
            PreparedStatement vst;
            vst = connection.prepareStatement(VehicleQuery.insert_Vehicle_Data);
            vst.setString(1, vehicle.getVnumber());
            vst.setString(2, vehicle.getVmodel());
            vst.setString(3, vehicle.getVcolour());
            vst.setInt(4, vehicle.getVyear());
            vst.setString(5, vehicle.getVstatus());
            vst.executeUpdate();
            resultValue = true;

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return resultValue;
    }

    public boolean updatevehicle(Vehicle vehicle) {
        boolean resultValue = false;

        try {
            PreparedStatement vst;
            vst = connection.prepareStatement(VehicleQuery.update_Vehicle_Data);

            vst.setString(1, vehicle.getVmodel());
            vst.setString(2, vehicle.getVcolour());
            vst.setInt(3, vehicle.getVyear());
            vst.setString(4, vehicle.getVstatus());
            vst.setString(5, vehicle.getVnumber());
            vst.execute();
            resultValue = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;

    }

    public boolean deletevehicle(Vehicle vehicle){
        boolean resultValue = false;
        try {
            PreparedStatement vst;
            vst = connection.prepareStatement(VehicleQuery.delete_Vehicle_Data);
            vst.setString(1, vehicle.getVnumber());
            vst.executeUpdate();
            resultValue = true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return resultValue;
    }

    public SortedList<Vehicle> searchTable(TextField searchTextField){

        ObservableList<Vehicle> vehicleData = getVehicleList();

        FilteredList<Vehicle> filteredData = new FilteredList<>(vehicleData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(vehicle -> {

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(vehicle.getVnumber().toLowerCase().indexOf(lowerCaseFilter) != -1){

                    return true;
                }else if(vehicle.getVmodel().toLowerCase().indexOf(lowerCaseFilter) != -1){

                    return true;
                }else if(vehicle.getVcolour().toLowerCase().indexOf(lowerCaseFilter) != -1){

                    return true;
                }else if(vehicle.getVstatus().toLowerCase().indexOf(lowerCaseFilter) !=-1){

                    return true;
                }else{
                    return false;
                }
            });
        });
        SortedList<Vehicle> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }




    }


