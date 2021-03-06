package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.VolunteerAvailabilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.Admin;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/volunteer")

public class VolunteerController {
    private VolunteerDao volunteerDao;
    private VolunteerAvailabilityDao volunteerAvailabilityDao;

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @Autowired
    public void setVolunteerAvailabilityDao (VolunteerAvailabilityDao volunteerAvailabilityDao){
        this.volunteerAvailabilityDao = volunteerAvailabilityDao;
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDeleteVolunteer(@PathVariable int id) {
        volunteerDao.deleteVolunteer(id);
        return "index";
    }

    @RequestMapping("/list")
    public String listVolunteer(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            UserDetails user = (UserDetails) session.getAttribute("user");

            if (session.getAttribute("role").equals("Admin") && user.getUsername().equals("casVolunteer")){
                List<Volunteer> listaAceptados = volunteerDao.getVolunteers();
                List<Volunteer> listaPendientes = volunteerDao.getVolunteersPendientes();
                List<Volunteer> listaRechazados = volunteerDao.getVolunteersRechazados();

                model.addAttribute("volunteers", listaAceptados);
                model.addAttribute("volunteersP", listaPendientes);
                model.addAttribute("volunteersR", listaRechazados);
                return "volunteer/list";

            } else if (session.getAttribute("role").equals("Elderly")){
                model.addAttribute("volAvailability", volunteerAvailabilityDao);
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Admin());
        model.addAttribute("login", true);
        return "login";
    }

    @RequestMapping(value="/add")
    public String addVolunteer(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        model.addAttribute("register", true);
        return "volunteer/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Volunteer volunteer,
                                   BindingResult bindingResult, Model model) {

        VolunteerValidator volunteerValidator = new VolunteerValidator();
        volunteerValidator.validate(volunteer, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("register", true);
            return "volunteer/add";
        }

        try {
            volunteerDao.addVolunteer(volunteer);
        } catch (DuplicateKeyException e){
            throw new MajorsacasaException(
                    "con el usuario: " + volunteer.getUsername(),
                    "CPduplicada");
        }

        return "redirect:../login";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editVolunteer(Model model, @PathVariable int id) {
        model.addAttribute("volunteer", volunteerDao.getVolunteer(id));
        return "volunteer/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("volunteer") Volunteer volunteer, BindingResult bindingResult,
                                      HttpSession session) {
        VolunteerValidator volunteerValidator = new VolunteerValidator();
        volunteerValidator.validate(volunteer, bindingResult);

        if (bindingResult.hasErrors()){
            return "volunteer/update";
        }

        volunteerDao.updateVolunteer(volunteer, (Integer) session.getAttribute("id"));
        return "redirect:/";
    }

    @RequestMapping(value = "/updateAceptar/{id}/{acceptDate}", method = RequestMethod.GET)
    public String updateVolunteerAcceptDate(@PathVariable int id, @PathVariable LocalDate acceptDate) {
        volunteerDao.updateVolunteerAccept(id, acceptDate);
        return "redirect:../../list";
    }

    @RequestMapping(value = "/updateReject/{id}/{state}", method = RequestMethod.GET)
    public String updateVolunteerAcceptDate(@PathVariable int id, @PathVariable String state) {
        volunteerDao.updateVolunteerReject(id, state);
        return "redirect:../../list";
    }

    @RequestMapping(value = "/updateFechaFin/{id}", method = RequestMethod.GET)
    public String updateVolunteerEndDate(@PathVariable int id) {

        volunteerDao.cancelVolunteer(id);

        return "redirect:../../logout";
    }
}
