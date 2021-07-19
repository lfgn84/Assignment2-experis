package se.experis.assignment2experis.Models;

public class Customer {
    private String CustomerId;
    private String FirstName;
    private String LastName;
    private String city;

    public Customer(){

    }

    public Customer(String customerId, String firstName, String lastName, String city, String phone) {
        CustomerId = customerId;
        FirstName = firstName;
        LastName = lastName;
        this.city = city;
        this.phone = phone;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;
}
