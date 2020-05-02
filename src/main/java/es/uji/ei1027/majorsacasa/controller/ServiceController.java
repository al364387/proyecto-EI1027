package es.uji.ei1027.majorsacasa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceController {
    @RequestMapping("/services")
    public String register(Model model) {
        model.addAttribute("info", true);
        return "services";
    }
}
