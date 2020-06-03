package es.uji.ei1027.majorsacasa.controller;

import javax.servlet.http.HttpSession;

import es.uji.ei1027.majorsacasa.dao.*;
import es.uji.ei1027.majorsacasa.model.*;
import es.uji.ei1027.majorsacasa.services.ElderlyService;
import es.uji.ei1027.majorsacasa.services.VolunteerService;
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
            errors.rejectValue("username", "obligatori", "Introduzca el nombre de usuario");
        }
        if (userDetails.getPassword().trim().equals("")) {
            errors.rejectValue("password", "obligatori", "Introduzca la contraseña");
        }
    }
}

@Controller
public class LoginController {

    private UserDao userDao;
    private AdminDao adminDao;
    private ElderlyDao elderlyDao;
    private VolunteerDao volunteerDao;
    private CompanyDao companyDao;
    private RequestDao requestDao;
    private InvoiceDao invoiceDao;

    private ElderlyService elderlyService;
    private VolunteerService volunteerService;



    @Autowired
    public void setLoginController(UserDao userDao, AdminDao adminDao, ElderlyDao elderlyDao,
                                   VolunteerDao volunteerDao, CompanyDao companyDao, RequestDao requestDao, InvoiceDao invoiceDao, ElderlyService elderlyService,
                                   VolunteerService volunteerService) {
        this.userDao = userDao;
        this.adminDao = adminDao;
        this.elderlyDao = elderlyDao;
        this.volunteerDao = volunteerDao;
        this.companyDao = companyDao;
        this.requestDao = requestDao;
        this.invoiceDao = invoiceDao;
        this.elderlyService = elderlyService;
        this.volunteerService = volunteerService;
    }

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
            role = "Admin";
            name = adminDao.getUserAdmin(user.getUsername()).getName();
            user = userDao.loadUserByUsername(user, adminDao.getUserAdmin(user.getUsername()).getPassword(),
                    role);
        } else if (elderlyDao.getUserElderly(user.getUsername()) != null) {
            role = "Elderly";
            name = elderlyDao.getUserElderly(user.getUsername()).getName();
            String dni = elderlyDao.getUserElderly(user.getUsername()).getDNI();
            user = userDao.loadUserByUsername(user, elderlyDao.getUserElderly(user.getUsername()).getPassword(),
                    role);
            session.setAttribute("requests", elderlyService.getRequestFormEldely(dni));
            session.setAttribute("elderlyService", elderlyService);
            session.setAttribute("volunteers", elderlyService.getLeisureTime(dni));
            session.setAttribute("dni", dni);
        } else if (volunteerDao.getUserVolunteer(user.getUsername()) != null) {
            role = "Volunteer";
            //Mirar si el voluntario tiene fecha de inicio y no tiene fecha fin
            Volunteer volunteer = volunteerDao.getUserVolunteer(user.getUsername());
            if (volunteer.getAcceptDate() != null && volunteer.getEndDate() == null) {
                name = volunteer.getName();
                int id = volunteer.getId();
                user = userDao.loadUserByUsername(user, volunteer.getPassword(),
                        role);
                session.setAttribute("volunteerService", volunteerService);
                session.setAttribute("listAvais", volunteerService.getListVolunteerAvailabilities(id));
                session.setAttribute("listAvaisOut", volunteerService.getListVolunteerAvailabilitiesOutElderly(id));
                session.setAttribute("id", id);
            } else {
                user = null;
            }
        } else if (companyDao.getUserCompany(user.getUsername()) != null) {
            role = "Company";
            name = companyDao.getUserCompany(user.getUsername()).getName();
            String cif = companyDao.getUserCompany(user.getUsername()).getCif();
            user = userDao.loadUserByUsername(user, companyDao.getUserCompany(user.getUsername()).getPassword(),
                    role);
            session.setAttribute("cif", cif);
            session.setAttribute("requestCompany", requestDao.getRequestsCompany(cif));
            session.setAttribute("requestCompanyCancel", requestDao.getRequestsCompanyCancel(cif));
            session.setAttribute("invoicesCompany", invoiceDao.getInvoicesCompany(cif));
            session.setAttribute("volunteerService", volunteerService);
        } else {
            user = null;
        }

        if (user == null) {
            bindingResult.rejectValue("username", "badpw", "Usuario incoreccto");
            bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta");
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
        } else {
            // Volver a la página principal
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}