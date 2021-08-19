package model;

public class Vehicle {
    private String vnumber;
    private String vmodel;
    private String vcolour;
    private Integer vyear;
    private String vstatus;

    public Vehicle(){

    }

    public Vehicle(String vnumber, String vmodel, String vcolour, Integer vyear, String vstatus){
        this.vnumber = vnumber;
        this.vmodel = vmodel;
        this.vcolour = vcolour;
        this.vyear = vyear;
        this.vstatus = vstatus;
    }

    public String getVnumber() {
        return vnumber;
    }

    public void setVnumber(String vnumber) {
        this.vnumber = vnumber;
    }

    public String getVmodel() {
        return vmodel;
    }

    public void setVmodel(String vmodel) {
        this.vmodel = vmodel;
    }

    public String getVcolour() {
        return vcolour;
    }

    public void setVcolour(String vcolour) {
        this.vcolour = vcolour;
    }

    public Integer getVyear() {
        return vyear;
    }

    public void setVyear(Integer vyear) {
        this.vyear = vyear;
    }

    public String getVstatus() {
        return vstatus;
    }

    public void setVstatus(String vstatus) {
        this.vstatus = vstatus;
    }
}
