package se.experis.assignment2experis.databaseHandler;

import org.springframework.stereotype.Repository;
import se.experis.assignment2experis.Models.Customer;
import se.experis.assignment2experis.Models.CustomerCountry;
import se.experis.assignment2experis.Models.CustomerFavoriteGenre;
import se.experis.assignment2experis.Models.CustomerSpender;

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

    public void connect(){
        System.out.println("Trying to connect to database ");
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        }catch (Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }

    }

    public Customer getCustomerById(int CustomerId){
        Customer customer = new Customer();
        try {
            // Open Connection
            connect();

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

    public ArrayList<Customer> selectAllCustomers(){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM customers");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

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

    public ArrayList<Customer> selectCustomersLike(String name){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM customers WHERE FirstName LIKE ?");
            preparedStatement.setString(1,"%"+name+"%");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

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

    public ArrayList<Customer> pageCustomers(int limit, int offset){
        ArrayList<Customer> customers = new ArrayList<Customer>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM customers LIMIT ? OFFSET ?");
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2, offset);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getInt("CustomerId"),
                                resultSet.getString("FirstName"),
                                resultSet.getString("LastName"),
                                resultSet.getString("Country"),
                                resultSet.getString("PostalCode"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email")

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

    public Boolean addCustomer(Customer customer){
        try{

            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO customers (FirstName,LastName,Country, PostalCode, Phone, Email ) VALUES (?,?,?,?,?,?);");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());

            preparedStatement.executeUpdate();
            //preparedStatement.executeQuery();

        }catch(Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return true;
        }
    }

    public Boolean updateCustomer(Customer customer){
        try{

            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customers SET FirstName = ?, LastName = ?, Country = ?, " +
                            "PostalCode = ?, Phone = ?, Email = ? WHERE CustomerId = ?;");
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getCountry());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, customer.getCustomerId());

            preparedStatement.executeUpdate();

        }catch(Exception ex){
            System.out.println("Something went wrong...");
            System.out.println(ex.toString());
        }finally {
            try {
                // Close Connection
                conn.close();
            }
            catch (Exception ex){
                System.out.println("Something went wrong while closing connection.");
                System.out.println(ex.toString());
            }
            return true;
        }
    }


    public ArrayList<CustomerCountry> CountryAmountInCustomers(){
      ArrayList<CustomerCountry> countryList = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(CustomerId) AS countryCount, Country FROM customers GROUP BY Country ORDER BY countryCount DESC ");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                countryList.add(
                        new CustomerCountry(resultSet.getString("Country"),
                                            resultSet.getInt("countryCount")  )
                );
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
            return countryList;
        }
    }

    public ArrayList<CustomerSpender> HighestSpendersInCustomers(){
        ArrayList<CustomerSpender> countryList = new ArrayList<>();
        try {
            // Open Connection
            conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT FirstName, SUM(invoices.total) AS total FROM customers " +
                            "INNER JOIN invoices ON customers.CustomerId = invoices.CustomerId GROUP BY FirstName ORDER BY total DESC");

            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                    countryList.add(new CustomerSpender(resultSet.getString("FirstName"),resultSet.getInt("total")));
               // countryList.add(resultSet.getString("FirstName") + ": " + resultSet.getString("total"));
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
            return countryList;
        }
    }

    public ArrayList<CustomerFavoriteGenre> customerFavoriteGenres(int id){
        ArrayList<CustomerFavoriteGenre> genreFavorites = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT COUNT(tracks.genreid) mycount, genres.Name, tracks.genreid gen, customers.firstname FROM customers INNER JOIN invoices ON customers.CustomerId = invoices.CustomerId " +
                            "INNER JOIN invoice_items ON invoices.InvoiceId = invoice_items.InvoiceId " +
                            "INNER JOIN tracks ON invoice_items.TrackId = tracks.TrackId INNER JOIN genres ON genres.GenreId = tracks.GenreId WHERE customers.CustomerId = ? GROUP BY tracks.genreid HAVING COUNT(tracks.GenreId) = ( " +
                            "SELECT MAX(mycount) " +
                            "FROM " +
                            "(SELECT COUNT(tracks.genreid) mycount, tracks.genreid gen, customers.firstname FROM customers INNER JOIN invoices ON customers.CustomerId = invoices.CustomerId " +
                            "INNER JOIN invoice_items ON invoices.InvoiceId = invoice_items.InvoiceId " +
                            "INNER JOIN tracks ON invoice_items.TrackId = tracks.TrackId WHERE customers.CustomerId = ? GROUP BY tracks.genreid))");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,id);
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                genreFavorites.add(
                        new CustomerFavoriteGenre(resultSet.getString("name"))
                );
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
            return genreFavorites;
        }
    }

}
