package com.example.profile.model;

public class User {
    private String fullName;
    private String taxCode;
    private String phone;
    private String email;
    private String address;

    public User() {}

    public User(String fullName, String taxCode, String phone, String email, String address) {
        this.fullName = fullName;
        this.taxCode = taxCode;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getter & Setter
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getTaxCode() { return taxCode; }
    public void setTaxCode(String taxCode) { this.taxCode = taxCode; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
