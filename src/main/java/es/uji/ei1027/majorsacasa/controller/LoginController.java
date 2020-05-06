package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.majorsacasa.dao.*;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> cls) {
        return UserDetails.class.isAssignableFrom(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserDetails userDetails = (UserDetails) obj;
        if (userDetails.getUsername().trim().equals("")) {
            errors.rejectValue("username", "obligatori", "Es necesario introducir el nombre de usuario");
        }

        if (userDetails.getPassword().trim().equals("")) {
            errors.rejectValue("password", "obligatori", "Es necesario introducir la contraseña");
        }
    }
}

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

        if (adminDao.getUserAdmin(user.getUsername()) != null) {
            role  = "Admin";
            user = userDao.loadUserByUsername(user, adminDao.getUserAdmin(user.getUsername()).getPassword(),
                    role);
        } else if (elderlyDao.getUserElderly(user.getUsername()) != null) {
            role ="Elderly";
            user = userDao.loadUserByUsername(user, elderlyDao.getUserElderly(user.getUsername()).getPassword(),
                    role);
        } else if (volunteerDao.getUserVolunteer(user.getUsername()) != null) {
            role = "Volunteer";
            user = userDao.loadUserByUsername(user, volunteerDao.getUserVolunteer(user.getUsername()).getPassword(),
                    role);
        } else if (companyDao.getUserCompany(user.getUsername()) != null) {
            role = "Company";
            user = userDao.loadUserByUsername(user, companyDao.getUserCompany(user.getUsername()).getPassword(),
                    role);
        } else {
            user = null;
        }

        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Usuario o contraseña incorrecta");
            model.addAttribute("login", true);
            return "login";
        }
        // Autenticados correctamente.
        // Guardamos los datos de el usuario autenticado en la sessión y su role
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