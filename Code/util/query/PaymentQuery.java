package util.query;

public class PaymentQuery {
    public final static String load_All_Payment_Data = "SELECT * FROM payment";
    public final static String insert_Payment_Data = "INSERT INTO payment (Payment_ID, Vehicle_No, Customer_Name, Customer_ID, Customer_TP, Amount) VALUES (?, ?, ?, ?, ?, ?)";
    public final static String update_Payment_Data = "UPDATE payment SET Vehicle_No = ?, Customer_Name = ?, Customer_ID = ?, Customer_TP = ?, Amount = ? WHERE Payment_ID = ?";
    public final static String delete_Payment_Data = "DELETE FROM payment WHERE Payment_ID = ?";

}
