package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/index")
    public String homeAdmin(HttpSession session, Model model) {
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new Admin());
            session.setAttribute("nextUrl", "admin/index");
            return "login";
        }
        model.addAttribute("isAdmin", true);

        return "admin";
    }

}
