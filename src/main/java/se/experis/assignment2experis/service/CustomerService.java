package se.experis.assignment2experis.service;

import org.springframework.stereotype.Service;
import se.experis.assignment2experis.models.Customer;
import se.experis.assignment2experis.models.CustomerCountry;
import se.experis.assignment2experis.models.CustomerFavoriteGenre;
import se.experis.assignment2experis.models.CustomerSpender;
import se.experis.assignment2experis.repository.CustomerRepository;

import java.util.ArrayList;

@Service
public class CustomerService {
    /**
     * Service class that handles methods from database repository to be applied on controller
     *
     */
    CustomerRepository customerRepository = new CustomerRepository();

    public Customer getCustomerById (int id){
      return   customerRepository.getCustomerById(id);
    }
    public ArrayList<Customer> getAllCustomers(){
        return customerRepository.selectAllCustomers();
    }
    public ArrayList<Customer> selectCustomersLike(String name){
        return customerRepository.selectCustomersLike(name);
    }
    public ArrayList<Customer> selectCustomerByName(String name){
        return customerRepository.selectCustomerByName(name);
    }
    public ArrayList<Customer> pageCustomers(int limit, int offset){
        return customerRepository.pageCustomers(limit,offset);
    }
    public Boolean addCustomer (Customer customer){
        return customerRepository.addCustomer(customer);
    }
    public Boolean updateCustomer (Customer customer){
        return customerRepository.updateCustomer(customer);
    }
    public ArrayList<CustomerCountry> CountryAmountInCustomers (){
        return customerRepository.CountryAmountInCustomers();
    }
    public ArrayList<CustomerSpender> HighestSpendersInCustomers (){
        return customerRepository.HighestSpendersInCustomers();
    }
    public ArrayList<CustomerFavoriteGenre> customerFavoriteGenres(int id){
        return customerRepository.customerFavoriteGenres(id);
    }
    public Boolean deleteCustomerById (int id){
        return customerRepository.deleteCustomerById(id);
    }
}
