package model;

        import javafx.beans.property.SimpleStringProperty;
        import javafx.beans.property.StringProperty;

public class User {
    private String uEmail = null;
    private String uFname = null;
    private String uLastName = null;
    private String uType = null;
    private String uPassword = null;
    private String uStatus = null;

    public User() {

    }

    public User(String uEmail, String uFname, String uLastName, String uType, String uPassword, String uStatus) {
        this.uEmail = uEmail;
        this.uFname = uFname;
        this.uLastName = uLastName;
        this.uType = uType;
        this.uPassword = uPassword;
        this.uStatus = uStatus;
    }

    public String getuEmail() {
        return uEmail;
    }

    public StringProperty uEmailProperty() {
        return new SimpleStringProperty(uEmail);
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuFname() {
        return uFname;
    }

    public StringProperty uFnameProperty() {
        return new SimpleStringProperty(uFname);
    }

    public void setuFname(String uFname) {
        this.uFname = uFname;
    }

    public String getuLastName() {
        return uLastName;
    }

    public StringProperty uLastNameProperty() {
        return new SimpleStringProperty(uLastName);
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuType() {
        return uType;
    }

    public StringProperty uTypeProperty() {
        return new SimpleStringProperty(uType);
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuPassword() {
        return uPassword;
    }

    public StringProperty uPasswordProperty() {
        return new SimpleStringProperty(uPassword);
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuStatus() {
        return uStatus;
    }

    public StringProperty uStatusProperty() {
        return new SimpleStringProperty(uStatus);
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }
}
