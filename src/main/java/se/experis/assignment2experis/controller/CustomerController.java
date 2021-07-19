package se.experis.assignment2experis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.experis.assignment2experis.Models.Customer;
import se.experis.assignment2experis.service.CustomerService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@RestController
@RequestMapping(path={"/api/customers"})
public class CustomerController {
    // Setup
    CustomerService service = new CustomerService();

    @GetMapping("getCustomerById/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return  service.getCustomerById(id);
    }

    @GetMapping("/all")
    public ArrayList<Customer> getAllCustomers(){
        return service.getAllCustomers();
    }

    @GetMapping("/selectCustomersLike/{name}")
    public ArrayList<Customer> selectCustomersLike(@PathVariable String name){
        return service.selectCustomersLike(name);
    }
    @GetMapping("/page/{limit},{offset}")
    public ArrayList<Customer> pageCustomers(@PathVariable int limit, @PathVariable int offset){
        return service.pageCustomers(limit, offset);
    }

    @GetMapping("/add")
    public Boolean addCustomer(){
        return service.addCustomer(new Customer(1,"Markus", "Wrang", "asd", "asd", "asd", "asd"));
    }
    @GetMapping("/update")
    public Boolean updateCustomer(){
        return service.updateCustomer(new Customer(62,"Luis", "Gutierrez", "asd", "asd", "asd", "asd"));
    }
    @GetMapping("/country")
    public ArrayList<String> CountryAmountInCustomers(){
        return service.CountryAmountInCustomers();
    }



    /*
    public ArrayList<Customer> selectAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Id,ContactName,City,Phone FROM customer");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("Id"),
                                resultSet.getString("ContactName"),
                                resultSet.getString("City"),
                                resultSet.getString("Phone")
                        ));
            }
        }
        catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }
        finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return customers;
        }
    }
*/
}
