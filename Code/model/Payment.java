package model;


public class Payment {
    private String Payment_ID;
    private String Vehicle_No;
    private String Customer_Name;
    private String Customer_ID;
    private String Customer_TP;
    private String Amount;


    public Payment() {

    }
    public Payment (String Payment_ID, String Vehicle_No, String Customer_Name, String Customer_ID, String Customer_TP, String Amount ) {
        this.Payment_ID = Payment_ID;
        this.Vehicle_No = Vehicle_No;
        this.Customer_Name = Customer_Name;
        this.Customer_ID = Customer_ID;
        this.Customer_TP = Customer_TP;
        this.Amount = Amount;
    }

    public String getPayment_ID() {
        return Payment_ID;
    }

    public void setPayment_ID(String payment_ID) {
        Payment_ID = payment_ID;
    }

    public String getVehicle_No() {
        return Vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        Vehicle_No = vehicle_No;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_TP() {
        return Customer_TP;
    }

    public void setCustomer_TP(String customer_TP) {
        Customer_TP = customer_TP;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
