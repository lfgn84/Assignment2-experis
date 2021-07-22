package se.experis.assignment2experis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.experis.assignment2experis.models.Customer;
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
    public String addCustomer(Model model) {
        model.addAttribute("addCustomer", new Customer());
        return "add-customer";
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer, Model model){
        Boolean success = customerService.addCustomer(customer);
        model.addAttribute("success", success);
        if(success){
            model.addAttribute("addCustomer", new Customer());
        }
        return "add-customer";
    }


}
