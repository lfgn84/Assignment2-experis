package se.experis.assignment2experis.repository;

import org.springframework.stereotype.Repository;
import se.experis.assignment2experis.models.SearchResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class ViewRepository {
    String URL = "jdbc:sqlite::resource:chinook.db";
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

    /**
     * Selects 5 random songs from the tracks table
     * @return A list of 5 random songs from the tracks table
     */
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
    /**
     * Selects 5 random artists from the artists table
     * @return A list of 5 random artists from the artists table
     */
    public ArrayList<String> get5RandomArtists(){
        ArrayList<String> randomArtists = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Name FROM artists WHERE artistid IN (SELECT artistid FROM artists ORDER BY RANDOM() LIMIT 5)");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                randomArtists.add(( resultSet.getString("Name")));
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
            return randomArtists;
        }
    }
    /**
     * Selects 5 random album titles from the albums table
     * @return A list of 5 random album titles from the albums table
     */
    public ArrayList<String> get5RandomAlbums(){
        ArrayList<String> randomAlbums = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT Title FROM albums WHERE albumid IN (SELECT albumid FROM albums ORDER BY RANDOM() LIMIT 5)");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                randomAlbums.add(( resultSet.getString("Title")));
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
            return randomAlbums;
        }
    }
    /**
     * takes a String as parameter and searches the databases genre, track, albums and artists. It check if the keyword
     * matches either tracks.Name, artists.Name, albums.Title or genres.name and returns all rows where a match is found.
     * @param keyWord the text you want to search for in the databases
     * @return A list of SearchResult object
     */
    public ArrayList<SearchResult> searchingByKeyword(String keyWord){
        ArrayList<SearchResult> resultsByKeyword = new ArrayList<>();
        try {
            // Open Connection
            connect();

            // Prepare Statement
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT tracks.Name track, artists.Name artist, albums.Title album, genres.Name genre FROM genres INNER JOIN tracks ON genres.GenreId = tracks.genreid INNER JOIN albums ON tracks.AlbumId = albums.AlbumId " +
                            "INNER JOIN artists ON albums.ArtistId  = artists.ArtistId WHERE tracks.name LIKE ? OR artists.Name LIKE ? OR albums.Title LIKE ? OR genres.Name LIKE ? ");
            preparedStatement.setString(1,"%"+keyWord+"%");
            preparedStatement.setString(2,"%"+keyWord+"%");
            preparedStatement.setString(3,"%"+keyWord+"%");
            preparedStatement.setString(4,"%"+keyWord+"%");
            // Execute Statement
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process Results
            while (resultSet.next()) {
                resultsByKeyword.add(
                        new SearchResult(resultSet.getString("track"),
                                  resultSet.getString("artist"),
                                  resultSet.getString("album"),
                                  resultSet.getString("genre")
                                )
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
            return resultsByKeyword;
        }
    }

}
