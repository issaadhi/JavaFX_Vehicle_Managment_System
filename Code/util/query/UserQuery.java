package util.query;

public class UserQuery {

    public final static String load_All_User_Data = "SELECT * FROM user";
    public final static String load_Specific_User_Data = "SELECT * FROM user WHERE uEmail = ?";
    public final static String insert_User_Data = "INSERT INTO user (uEmail, uFName, uLastName, uType, uPassword, uStatus) VALUES (?, ?, ?, ?, ?, ?)";
    public final static String update_User_Data = "UPDATE user SET uFName = ?, uLastName = ?, uType = ?, uPassword = ?, uStatus = ? WHERE uEmail = ?";
    public final static String update_User_Password = "UPDATE user SET uPassword = ? WHERE uEmail = ?";
    public final static String delete_User_Data = "DELETE FROM user WHERE uEmail = ?";



}
