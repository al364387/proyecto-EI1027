package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    private final List<String> images = new ArrayList<>();

    @Autowired
    public void setImages(){
        images.add("/img/slide1.png");
        images.add("/img/slide2.png");
        images.add("/img/slide3.png");
        images.add("/img/slide4.png");
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("images", images);
        model.addAttribute("home", true);
        return "index";
    }

    @RequestMapping("/index")
    public String index1(Model model) {
        model.addAttribute("images", images);
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
