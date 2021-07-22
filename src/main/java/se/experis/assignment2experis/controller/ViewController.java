package se.experis.assignment2experis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.experis.assignment2experis.models.Customer;
import se.experis.assignment2experis.service.CustomerService;
import se.experis.assignment2experis.service.ViewService;


@Controller
public class ViewController {
    ViewService service = new ViewService();
    CustomerService customerService = new CustomerService();

    /**
     * Index page containing a list of 5 random songs, list of 5 random artist, list of 5 random albums
     * and a search bar to look for tracks, songs, artist and albums matching specified parameter
     * in any way
     * @param model Model for Thymeleaf
     * @param keyWord String used for search query
     * @return HTML template for index.html
     */
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
    /**
     * Page containing a list of all customers
     * @param model Model for Thymeleaf
     * @return HTML template for view-all-customers.html
     */
    @GetMapping("/all")
    public String getAllCustomers(Model model){
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        return "view-all-customers";
    }

    /**
     * Page where we can create a new customer object
     * @param model Model for Thymeleaf
     * @return HTML template for add-customer.html
     */
    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }
    /**
     * Method where we can create a new customer object
     * @param model Model for Thymeleaf
     * @return HTML template for add-customer.html
     */
    @PostMapping("/add")
    public String addCustomer(Customer customer, Model model){
        Boolean success = customerService.addCustomer(customer);
        model.addAttribute("success", success);
        if(success){
            model.addAttribute("customer", new Customer());
        }
        return "add-customer";
    }


}
