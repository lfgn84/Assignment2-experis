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

    // Setup
    CustomerService service = new CustomerService();
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    /**
     * Gets Customer object by id
     * @param id
     * @return Customer object
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return  service.getCustomerById(id);
    }

    /**
     * Get all Customer objects
     * @return an array list of all Customer objects
     */
    @GetMapping("/all")
    public ArrayList<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }
    /**
     *
     * @param name
     * @return an array list of Customer objects where FirstName or LastName matches parameter exactly
     */
    @GetMapping("/selectCustomerByName/{name}")
    public ArrayList<Customer> selectCustomerBySpecificName(@PathVariable String name){
        return service.selectCustomerByName(name);
    }

    /**
     *
     * @param name
     * @return an ArrayList of Customer objects where FirstName or LastName matches parameter in any way
     */
    @GetMapping("/selectCustomersLike/{name}")
    public ArrayList<Customer> selectCustomersLike(@PathVariable String name){
        return service.selectCustomersLike(name);
    }

    /**
     *
     * @param limit how large the ArrayList can be
     * @param offset the index where the ArrayList will start
     * @return an ArrayList of Customer objects
     */
    @GetMapping("/page/{limit},{offset}")
    public ArrayList<Customer> pageCustomers(@PathVariable int limit, @PathVariable int offset){
        return service.pageCustomers(limit, offset);
    }

    /**
     *
     * @param customer
     * @return Boolean if adding a Customer object has been successful or not
     */
    @PostMapping("/add")
    public Boolean addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    /**
     *
     * @param id Customer id
     * @param customer Customer object
     * @return Boolean if updating Customer object has been successful or not
     */
    @PutMapping("/{id}")
    public Boolean updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        return service.updateCustomer(customer);
    }

    /**
     *
     * @return ArrayList of number of Customer objects in each country in descending order
     */
    @GetMapping("/country")
    public ArrayList<CustomerCountry> countryAmountInCustomers(){
        return service.CountryAmountInCustomers();
    }

    /**
     *
     * @return ArrayList of CustomerSpenders ordered by the highest spenders
     */
    @GetMapping("/spenders")
    public ArrayList<CustomerSpender> highestSpendersInCustomers(){
        return service.HighestSpendersInCustomers();
    }

    /**
     *
     * @param id
     * @return an ArrayList of the customer CustomerFavoriteGenre containing the customer's favorite genre
     */
    @GetMapping("/favourites/{id}")
    public ArrayList<CustomerFavoriteGenre> customerFavoriteGenres(@PathVariable int id){
       return service.customerFavoriteGenres(id);
    }

    /**
     *
     * @param id
     * @return Boolean if deleting Customer object is successful or not
     */
    @DeleteMapping("/{id}")
    public Boolean deleteCustomerById(@PathVariable int id){
        return service.deleteCustomerById(id);
    }

}
