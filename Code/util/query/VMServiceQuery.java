package util.query;

public class VMServiceQuery {

    public final static String load_All_VehicleService_Data = "SELECT * FROM vehicle_service";
    public final static String insert_VehicleService_Data = "INSERT INTO vehicle_service (Service_id, Vehicle_id, Service_description, Service_amount, Service_date) VALUES (?, ?, ?, ?, ?)";
    public final static String update_Maintenance_Data = "UPDATE vehicle_service SET Vehicle_id = ?, Service_description = ?, Service_amount = ?, Service_date = ? WHERE Service_id = ?";
    public final static String delete_Maintenance_Data = "DELETE FROM vehicle_service WHERE Service_id = ?";


}
