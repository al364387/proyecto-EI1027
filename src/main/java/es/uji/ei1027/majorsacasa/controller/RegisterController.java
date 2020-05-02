package es.uji.ei1027.majorsacasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", true);
        return "register";
    }
}
