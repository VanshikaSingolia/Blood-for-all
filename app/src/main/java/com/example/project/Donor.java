package com.example.project;

public class Donor {
    private String d_area;
    private String d_email;
    private String d_phone;
    private String d_status;
    private String d_image;
    private String d_name;
    private String d_blood;

    public Donor(String d_name, String d_blood, String d_area, String d_email, String d_phone, String d_status, String d_image) {
        this.d_name = d_name;
        this.d_blood = d_blood;
        this.d_area = d_area;
        this.d_email = d_email;
        this.d_phone = d_phone;
        this.d_status = d_status;
        this.d_image = d_image;
    }

    public String getD_area() {
        return d_area;
    }

    public String getD_email() {
        return d_email;
    }

    public String getD_phone() {
        return d_phone;
    }

    public String getD_status() {
        return d_status;
    }

    public String getD_image() {
        return d_image;
    }

    public String getD_name() {
        return d_name;
    }

    public String getD_blood() {
        return d_blood;
    }
}
