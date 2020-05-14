package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.Admin;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDeleteVolunteer(@PathVariable int id) {
        volunteerDao.deleteVolunteer(id);
        return "redirect:../list";
    }

    @RequestMapping("/list")
    public String listVolunteer(HttpSession session, Model model) {
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new Admin());
            model.addAttribute("login", true);
            return "login";
        }

        List<Volunteer> l = volunteerDao.getVolunteers();

        model.addAttribute("isAdmin", true);
        model.addAttribute("volunteers", l);
        return "volunteer/list";
    }

    @RequestMapping(value="/add")
    public String addVolunteer(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteer/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Volunteer volunteer,
                                   BindingResult bindingResult) {


        VolunteerValidator volunteerValidator = new VolunteerValidator();
        volunteerValidator.validate(volunteer, bindingResult);

        if (bindingResult.hasErrors())
            return "volunteer/add";

        try {
            volunteerDao.addVolunteer(volunteer);
        } catch (Exception e){
            throw new MajorsacasaException(
                    "Un voluntario no puede ser menor de edad", "edadVoluntario");
        }

        return "redirect:/index";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editVolunteer(Model model, @PathVariable int id) {
        model.addAttribute("volunteer", volunteerDao.getVolunteer(id));
        return "volunteer/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("volunteer") Volunteer volunteer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "volunteer/update";
        volunteerDao.updateVolunteer(volunteer);
        return "redirect:list";
    }

    @RequestMapping(value = "/update/{id}/{acceptDate}", method = RequestMethod.GET)
    public String updateVolunteerAcceptDate(@PathVariable int id, @PathVariable LocalDate acceptDate) {
        volunteerDao.updateVolunteerAcceptDate(id, acceptDate);
        return "redirect:../../list";
    }


}
