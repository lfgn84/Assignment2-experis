package se.experis.assignment2experis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import se.experis.assignment2experis.service.CustomerService;
import se.experis.assignment2experis.service.ViewService;


@Controller
public class ViewController {
    ViewService service = new ViewService();
    CustomerService customerService = new CustomerService();

    @GetMapping("/")
    public String list(Model model ,@ModelAttribute(value = "keyWord") String keyWord){
        model.addAttribute("randomSongs", service.get5RandomSongs());
        model.addAttribute("randomArtists", service.get5RandomArtists());
        model.addAttribute("randomAlbums",service.get5RandomAlbums());
        if(!keyWord.equals("")){
        model.addAttribute("searching", service.getTracksByKeyword(keyWord));
        }
        return "index";
    }

    @GetMapping("/all")
    public String getAllCustomers(Model model){
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        return "view-all-customers";
    }

    @GetMapping("/add")
    public String addCustomers(Model model){

        return "add-customer";
    }


}
