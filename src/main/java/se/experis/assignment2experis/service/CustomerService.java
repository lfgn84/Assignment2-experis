package se.experis.assignment2experis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.experis.assignment2experis.Models.Customer;
import se.experis.assignment2experis.databaseHandler.DataBaseHandler;

import java.util.ArrayList;

@Service
public class CustomerService {

    DataBaseHandler dataBaseHandler = new DataBaseHandler();

    public Customer getCustomerById (int id){
      return   dataBaseHandler.testConnection(id);
    }
}
