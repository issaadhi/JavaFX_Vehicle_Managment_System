package services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.User;
import util.dbConnection.DBConnection;
import util.query.UserQuery;
import util.utility.Encryption;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public ObservableList<User>loadAllUser(){
        ObservableList<User> userObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(UserQuery.load_All_User_Data);
            while(resultSet.next()){
                userObservableList.add(new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userObservableList;
    }
    private Connection connection;

    public UserService(){
        connection = DBConnection.getDBConnection();
    }

    public User loadSpecificUser(String uEmail){
        User user = null;


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.load_Specific_User_Data);
            preparedStatement.setString(1, uEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6) );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    public boolean insertUserData(User user){
        boolean resultValue = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.insert_User_Data);
            preparedStatement.setString(1, user.getuEmail());
            preparedStatement.setString(2, user.getuFname());
            preparedStatement.setString(3, user.getuLastName());
            preparedStatement.setString(4, user.getuType());
            preparedStatement.setString(5, Encryption.passwordEncryption(user.getuPassword()));
            preparedStatement.setString(6, user.getuStatus());

            preparedStatement.execute();
            resultValue = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;
    }


    public boolean updatetUserData(User user){
        boolean resultValue = false;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.update_User_Data);

            preparedStatement.setString(1, user.getuFname());
            preparedStatement.setString(2, user.getuLastName());
            preparedStatement.setString(3, user.getuType());
            preparedStatement.setString(4, Encryption.passwordEncryption(user.getuPassword()));
            preparedStatement.setString(5, user.getuStatus());
            preparedStatement.setString(6, user.getuEmail());

            preparedStatement.execute();
            resultValue = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    public boolean updateUserPassword(User user){
        boolean resultValue = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.update_User_Password);

            preparedStatement.setString(1, Encryption.passwordEncryption(user.getuPassword()));
            preparedStatement.setString(2, user.getuEmail());

            preparedStatement.execute();
            resultValue = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultValue;
    }




    public boolean deleteUser(User user){
        boolean resultValue = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.delete_User_Data);
            preparedStatement.setString(1, user.getuEmail());
            preparedStatement.executeUpdate();
            resultValue = true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return resultValue;
    }


    public SortedList<User> searchTable(TextField searchTextField){
        //Retreiving all data from database
        ObservableList<User> usersData = loadAllUser();

        //Wrap the ObservableList in a filtered List (initially display all data)
        FilteredList<User> filteredData = new FilteredList<>(usersData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(user -> {
                //if filter text is empty display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                //comparing search text with table columns one by one
                String lowerCaseFilter = newValue.toLowerCase();

                if(user.getuEmail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuFname().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuLastName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuType().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    //return if filter matches data
                    return true;
                }else if(user.getuStatus().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                    //return if filter matches data
                    return true;
                }else{
                    //have no matchings
                    return false;
                }
            });
        });
        //wrapping the FilteredList in a SortedList
        SortedList<User> sortedData = new SortedList<>(filteredData);
        return sortedData;
    }





}
