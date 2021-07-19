package se.experis.assignment2experis.databaseHandler;

import org.springframework.stereotype.Repository;
import se.experis.assignment2experis.Models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class DataBaseHandler {
    String URL = "jdbc:sqlite::resource:chinook.db";
    //String URL = "spring.datasource.url";
    Connection conn = null;

    public Customer testConnection(int CustomerId){
        Customer customer = new Customer();
        try {
            // Open Connection
            System.out.println("Trying to connect to database ");
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM customers WHERE CustomerId = ?");
            preparedStatement.setInt(1,CustomerId);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            if (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt("CustomerId"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setCountry(resultSet.getString("Country"));
                customer.setPostalCode(resultSet.getString("Postalcode"));
                customer.setPhone(resultSet.getString("Phone"));
                customer.setEmail(resultSet.getString("Email"));

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

        }
        return customer;
    }
}
