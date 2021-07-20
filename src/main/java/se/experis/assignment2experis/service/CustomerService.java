package se.experis.assignment2experis.service;

import org.springframework.stereotype.Service;
import se.experis.assignment2experis.Models.Customer;
import se.experis.assignment2experis.Models.CustomerCountry;
import se.experis.assignment2experis.Models.CustomerFavoriteGenre;
import se.experis.assignment2experis.Models.CustomerSpender;
import se.experis.assignment2experis.databaseHandler.DataBaseHandler;

import java.util.ArrayList;

@Service
public class CustomerService {

    DataBaseHandler dataBaseHandler = new DataBaseHandler();

    public Customer getCustomerById (int id){
      return   dataBaseHandler.getCustomerById(id);
    }
    public ArrayList<Customer> getAllCustomers(){
        return dataBaseHandler.selectAllCustomers();
    }
    public ArrayList<Customer> selectCustomersLike(String name){
        return dataBaseHandler.selectCustomersLike(name);
    }
    public ArrayList<Customer> pageCustomers(int limit, int offset){
        return dataBaseHandler.pageCustomers(limit,offset);
    }
    public Boolean addCustomer (Customer customer){
        return dataBaseHandler.addCustomer(customer);
    }
    public Boolean updateCustomer (Customer customer){
        return dataBaseHandler.updateCustomer(customer);
    }
    public ArrayList<CustomerCountry> CountryAmountInCustomers (){
        return dataBaseHandler.CountryAmountInCustomers();
    }
    public ArrayList<CustomerSpender> HighestSpendersInCustomers (){
        return dataBaseHandler.HighestSpendersInCustomers();
    }
    public ArrayList<CustomerFavoriteGenre> customerFavoriteGenres(int id){
        return dataBaseHandler.customerFavoriteGenres(id);
    }
}
