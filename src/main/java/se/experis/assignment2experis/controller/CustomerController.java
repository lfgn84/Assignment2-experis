package se.experis.assignment2experis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import se.experis.assignment2experis.models.Customer;
import se.experis.assignment2experis.models.CustomerCountry;
import se.experis.assignment2experis.models.CustomerFavoriteGenre;
import se.experis.assignment2experis.models.CustomerSpender;
import se.experis.assignment2experis.service.CustomerService;

import java.util.ArrayList;


@RestController
@RequestMapping(path={"/api/customers"})
public class CustomerController {


    CustomerService service = new CustomerService();
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return  service.getCustomerById(id);
    }

    @GetMapping("/all")
    public ArrayList<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    @GetMapping("/selectCustomerByName/{name}")
    public ArrayList<Customer> selectCustomerBySpecificName(@PathVariable String name){
        return service.selectCustomerByName(name);
    }

    @GetMapping("/selectCustomersLike/{name}")
    public ArrayList<Customer> selectCustomersLike(@PathVariable String name){
        return service.selectCustomersLike(name);
    }
    @GetMapping("/page/{limit},{offset}")
    public ArrayList<Customer> pageCustomers(@PathVariable int limit, @PathVariable int offset){
        return service.pageCustomers(limit, offset);
    }
    @PostMapping("/add")
    public Boolean addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }
    @PutMapping("/{id}")
    public Boolean updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return service.updateCustomer(customer);
    }
    @GetMapping("/country")
    public ArrayList<CustomerCountry> countryAmountInCustomers(){
        return service.CountryAmountInCustomers();
    }
    @GetMapping("/spenders")
    public ArrayList<CustomerSpender> highestSpendersInCustomers(){
        return service.HighestSpendersInCustomers();
    }
    @GetMapping("/favourites/{id}")
    public ArrayList<CustomerFavoriteGenre> customerFavoriteGenres(@PathVariable int id){
       return service.customerFavoriteGenres(id);
    }
    @DeleteMapping("/{id}")
    public Boolean deleteCustomerById(@PathVariable int id){
        return service.deleteCustomerById(id);
    }

}
