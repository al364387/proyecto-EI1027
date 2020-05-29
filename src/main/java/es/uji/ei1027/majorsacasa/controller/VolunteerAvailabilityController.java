package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.VolunteerAvailabilityDao;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/volunteerAvailability")
public class VolunteerAvailabilityController {
    private VolunteerAvailabilityDao volunteerAvailabilityDao;

    @Autowired
    public void setVolunteerDao(VolunteerAvailabilityDao volunteerAvailabilityDao) {
        this.volunteerAvailabilityDao = volunteerAvailabilityDao;
    }

    @RequestMapping(value = "/add")
    public String addRequest(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Volunteer")) {

                session.setAttribute("volunteerAvailable", new VolunteerAvailability());
                return "volunteerAvailability/add";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Volunteer());
        model.addAttribute("login", true);

        return "login";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteerAvailable") VolunteerAvailability volunteerAvailability,
                                   BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "volunteerAvailability/add";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/addElderly")
    public String addRequestElderly(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {
                session.setAttribute("volunteerAva", new VolunteerAvailability());
                session.setAttribute("volunteersAvailable", volunteerAvailabilityDao.getAllVolunteerAvailabilities());
                return "volunteerAvailability/addElderly";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Volunteer());
        model.addAttribute("login", true);

        return "login";
    }

    @RequestMapping(value = "/addElderly", method = RequestMethod.POST)
    public String processAddSubmitElderly(@ModelAttribute("volunteerAva") VolunteerAvailability volunteerAvailability,
                                          BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "volunteerAvailability/addElderly";
        }

        volunteerAvailabilityDao.updateVolunteerAvailabilityAddElderly(volunteerAvailability.getId(),
                volunteerAvailability.getDniEderly());

        return "redirect:/";
    }

    @RequestMapping(value = "/infoVolunteer/{idVolunteer}/{dni}", method = RequestMethod.GET)
    public String infoRequest(@PathVariable int idVolunteer, @PathVariable String dni, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                session.setAttribute("volunteer",
                        volunteerAvailabilityDao.getVolunteerAvailabilityWithElderly(idVolunteer, dni));

                return "/volunteerAvailability/infoVolunteer";
            }
        }

        return "redirect:../../login";
    }

    @RequestMapping(value = "/cancel/{idVolunteer}/{dni}", method = RequestMethod.GET)
    public String cancelRequest(@PathVariable int idVolunteer, @PathVariable String dni, HttpSession session){

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                try{

                } catch (DataIntegrityViolationException e){

                }

                return "redirect:../../";
            }
        }

        return  "redirect:../../login";
    }
}
