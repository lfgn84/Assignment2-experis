package se.experis.assignment2experis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.experis.assignment2experis.service.ViewService;


@Controller
public class SearchController {
    ViewService service = new ViewService();

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/index/list")
    public String list(Model model){
        model.addAttribute("randomSongs", service.get5RandomSongs());
        return "Lists";
    }
}
