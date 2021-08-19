package util.query;

public class VehicleQuery {
    public final static String load_All_Vehicle_Data = "SELECT * FROM vehicle";
    public final static String insert_Vehicle_Data = "INSERT INTO vehicle (vnumber, vmodel, vcolour, vyear, vstatus) VALUES (?, ?, ?, ?, ?)";
    public final static String update_Vehicle_Data = "UPDATE vehicle SET vmodel = ?, vcolour = ?, vyear = ?, vstatus = ? WHERE vnumber = ?";
    public final static String delete_Vehicle_Data = "DELETE FROM vehicle WHERE vnumber = ?";

}
