package se.experis.assignment2experis.service;

import org.springframework.stereotype.Service;
import se.experis.assignment2experis.repository.ViewRepository;

import java.util.ArrayList;

@Service
public class ViewService {
    ViewRepository viewRepository = new ViewRepository();

    public ArrayList<String> get5RandomSongs(){
      return  viewRepository.get5RandomSongs();
    }
}
