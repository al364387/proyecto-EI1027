package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.majorsacasa.dao.*;
import es.uji.ei1027.majorsacasa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Controller
public class LoginController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private ElderlyDao elderlyDao;

    @Autowired
    private VolunteerDao volunteerDao;

    @Autowired
    private CompanyDao companyDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user, Model model,
                             BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("login", true);
            return "login";
        }
        // Comprueba que el login sea correcto
        // intentando cargar los datos de usuario
        String role = "";
        String name = "";

        if (adminDao.getUserAdmin(user.getUsername()) != null) {
            role  = "Admin";
            name = adminDao.getUserAdmin(user.getUsername()).getName();
            user = userDao.loadUserByUsername(user, adminDao.getUserAdmin(user.getUsername()).getPassword(),
                    role);
        } else if (elderlyDao.getUserElderly(user.getUsername()) != null) {
            role ="Elderly";
            name = elderlyDao.getUserElderly(user.getUsername()).getName();
            user = userDao.loadUserByUsername(user, elderlyDao.getUserElderly(user.getUsername()).getPassword(),
                    role);
        } else if (volunteerDao.getUserVolunteer(user.getUsername()) != null) {
            role = "Volunteer";
            //Mirar si el voluntario tiene fecha de inicio y no tiene fecha fin
            Volunteer volunteer = volunteerDao.getUserVolunteer(user.getUsername());
            if(volunteer.getAcceptDate() != null && volunteer.getEndDate() == null) {
                name = volunteer.getName();
                user = userDao.loadUserByUsername(user, volunteer.getPassword(),
                        role);
            }else{
                user = null;
            }
        } else if (companyDao.getUserCompany(user.getUsername()) != null) {
            role = "Company";
            name = companyDao.getUserCompany(user.getUsername()).getName();
            user = userDao.loadUserByUsername(user, companyDao.getUserCompany(user.getUsername()).getPassword(),
                    role);
        } else {
            user = null;
        }

        if (user == null) {
            bindingResult.rejectValue("username", "badpw", "");
            bindingResult.rejectValue("password", "badpw", "Usuario o contraseña incorrecta");
            model.addAttribute("login", true);
            return "login";
        }
        // Autenticados correctamente.
        // Guardamos los datos de el usuario autenticado en la sessión y su role
        session.setAttribute("nameUser", name);
        session.setAttribute("user", user);
        session.setAttribute("role", role);
        String nextURL = (String) session.getAttribute("nextUrl");
        if (nextURL != null) {
            session.removeAttribute("nextUrl");
            return "redirect:/" + nextURL;
        }
        // Volver a la página principal
        else
            return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}