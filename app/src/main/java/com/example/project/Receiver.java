package com.example.project;

public class Receiver {

    private String r_name;
    private String date;
    private String r_phone;
    private String r_email;
    private String purpose;
    private String bloodgroup;
    private String area;

    public Receiver( String r_name, String date, String r_phone, String r_email, String purpose, String bloodgroup, String area) {
        this.r_name = r_name;
        this.date = date;
        this.r_phone = r_phone;
        this.r_email = r_email;
        this.purpose = purpose;
        this.bloodgroup = bloodgroup;
        this.area=area;
    }

    public String getR_name() {
        return r_name;
    }

    public String getR_Area() {
        return area;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public String getDate1() {
        return date;
    }



    public String getR_phone() {
        return r_phone;
    }



    public String getR_email() {
        return r_email;
    }



    public String getPurpose() {
        return purpose;
    }


}
