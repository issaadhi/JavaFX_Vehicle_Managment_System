package model;

public class VMService {

    private String Service_id;
    private String Vehicle_id;
    private String Service_description;
    private String Service_amount;
    private String Service_date;


    public VMService() {

    }

    public VMService(String Service_id, String Vehicle_id, String Service_description, String Service_amount, String Service_date) {
        this.Service_id = Service_id;
        this.Vehicle_id = Vehicle_id;
        this.Service_description = Service_description;
        this.Service_amount = Service_amount;
        this.Service_date = Service_date;
    }

    public String getService_id() {
        return Service_id;
    }

    public void setService_id(String service_id) {
        this.Service_id = service_id;
    }

    public String getService_description() {
        return Service_description;
    }

    public void setService_description(String service_description) {
        this.Service_description = service_description;
    }

    public String getService_amount() {
        return Service_amount;
    }

    public void setService_amount(String service_amount) {
        this.Service_amount = service_amount;
    }

    public String getService_date() {
        return Service_date;
    }

    public void setService_date(String service_date) {
        this.Service_date = service_date;
    }

    public String getVehicle_id() { return Vehicle_id; }

    public void setVehicle_id(String vehicle_id) { Vehicle_id = vehicle_id; }
}
