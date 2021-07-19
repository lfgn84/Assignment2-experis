package se.experis.assignment2experis.Models;

public class Customer {
    private int CustomerId;
    private String FirstName;
    private String LastName;
    private String Country;
    private String PostalCode;
    private String Phone;
    private String Email;

    public Customer(int customerId, String firstName, String lastName, String country, String postalCode, String phone, String email) {
        CustomerId = customerId;
        FirstName = firstName;
        LastName = lastName;
        Country = country;
        PostalCode = postalCode;
        Phone = phone;
        Email = email;
    }

    public Customer(){

    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

}
