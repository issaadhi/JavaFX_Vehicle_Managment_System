package util.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    /*
     * Singleton design pattern is implemented to avoid creation of multiple instances of DBConnetion
     */
    private DBConnection() {
    }

    public static Connection getDBConnection(){

        if (conn == null) {
            DBConnection.getConnection();
        }
        return conn;
    }

    /*
     * initiates a new connection to database
     * @param {}
     * @returns void
     */
    private static void getConnection(){
        /*
         * local mysql database credentials
         */
        String url = "jdbc:mysql://localhost/";
        String dbname = "vms";
        String ssl = "?useSSL=false";
        String username = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbname + ssl, username, password);
        }catch(SQLException | ClassNotFoundException exception ){

        }

    }
}
