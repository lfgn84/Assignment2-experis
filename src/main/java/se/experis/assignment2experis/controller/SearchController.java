package se.experis.assignment2experis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import se.experis.assignment2experis.models.ViewSearch;
import se.experis.assignment2experis.service.ViewService;


@Controller
public class SearchController {
    ViewService service = new ViewService();

    @GetMapping("/")
    public String list(Model model ,@ModelAttribute(value = "keyWord") String keyWord){
        model.addAttribute("randomSongs", service.get5RandomSongs());
        model.addAttribute("randomArtists", service.get5RandomArtists());
        model.addAttribute("randomAlbums",service.get5RandomAlbums());
        model.addAttribute("searching", service.getTracksByKeyword(keyWord));
        return "index";
    }


}
