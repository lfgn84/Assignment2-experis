package se.experis.assignment2experis.repository;

import org.springframework.stereotype.Repository;
import se.experis.assignment2experis.models.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class ViewRepository {
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

    public ArrayList<String> get5RandomSongs(){
        ArrayList<String> randomSongs = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM tracks WHERE trackid IN (SELECT trackid FROM tracks ORDER BY RANDOM() LIMIT 5)");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                randomSongs.add(( resultSet.getString("Name")));
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
            return randomSongs;
        }
    }
}
