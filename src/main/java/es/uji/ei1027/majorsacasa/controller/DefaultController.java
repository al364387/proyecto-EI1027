package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("home", true);
        return "index";
    }

    @RequestMapping("/index")
    public String index1(Model model){
        model.addAttribute("home", true);
        return "index";
    }

    @RequestMapping("/services")
    public String services(Model model) {
        model.addAttribute("info", true);
        return "services";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", true);
        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new Admin());
        model.addAttribute("login", true);
        return "login";
    }
}
