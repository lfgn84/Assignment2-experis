package se.experis.assignment2experis.models;

public class CustomerCountry {
    private String country;
    private int customers;

    public CustomerCountry(String country, int customers) {
        this.country = country;
        this.customers = customers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }
}
