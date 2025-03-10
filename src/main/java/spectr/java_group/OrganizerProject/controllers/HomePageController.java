package spectr.java_group.OrganizerProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    private String homePage(){
        System.out.println("Home");
        if (1 > 0) return "login";
        else return "home";
    }
}
