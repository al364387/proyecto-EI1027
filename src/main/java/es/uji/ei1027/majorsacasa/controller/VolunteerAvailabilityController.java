package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.VolunteerAvailabilityDao;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import es.uji.ei1027.majorsacasa.services.ElderlyService;
import es.uji.ei1027.majorsacasa.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private VolunteerService volunteerService;

    @Autowired
    public void setVolunteerDao(VolunteerAvailabilityDao volunteerAvailabilityDao, VolunteerService volunteerService) {
        this.volunteerAvailabilityDao = volunteerAvailabilityDao;
        this.volunteerService = volunteerService;
    }

    @RequestMapping(value = "/add")
    public String addVolunteerAvailability(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Volunteer")) {
                model.addAttribute("volunteerAvailability", new VolunteerAvailability());
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
    public String processAddSubmitVolunteerAvailability(@ModelAttribute("volunteerAvailability") VolunteerAvailability volunteerAvailability,
                                                        BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "volunteerAvailability/add";
        }

        volunteerAvailabilityDao.addVolunteerAvailability(volunteerAvailability, (Integer) session.getAttribute("id"));

        return "redirect:/";
    }

    @RequestMapping(value = "/addElderly")
    public String addRequestElderly(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {
                model.addAttribute("volunteerAva", new VolunteerAvailability());
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

    @RequestMapping(value = "/addElderly/{id}", method = RequestMethod.GET)
    public String processAddSubmitElderly(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                String dni = (String) session.getAttribute("dni");
                volunteerAvailabilityDao.updateVolunteerAvailabilityAddElderly(id, dni);

                return "redirect:../../";
            }
        }

        return "redirect:../../login";
    }

    @RequestMapping(value = "/infoVolunteer/{idVolunteer}/{dni}", method = RequestMethod.GET)
    public String infoRequest(@PathVariable int idVolunteer, @PathVariable String dni, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                ElderlyService elderlyService = (ElderlyService) session.getAttribute("elderlyService");

                session.setAttribute("nameV", elderlyService.getNameVolunteer(idVolunteer));
                session.setAttribute("phoneV", elderlyService.getPhoneVolunteer(idVolunteer));

                session.setAttribute("volunteerElderly",
                        volunteerAvailabilityDao.getVolunteerAvailabilityWithElderly(idVolunteer, dni));

                return "/volunteerAvailability/infoVolunteer";
            }
        }

        return "redirect:../../login";
    }

    @RequestMapping(value = "/infoVolunteer/{id}", method = RequestMethod.GET)
    public String infoRequest2(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Volunteer")) {

                session.setAttribute("volunteerService", volunteerService);

                session.setAttribute("volunteerElderly",
                        volunteerAvailabilityDao.getVolunteerAvailability(id));

                return "/volunteerAvailability/infoVolunteer";
            }
        }

        return "redirect:../../login";
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
    public String cancelRequest(@PathVariable int id, HttpSession session) {

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly") || session.getAttribute("role").equals("Volunteer")) {

                volunteerAvailabilityDao.cancelVolunteerAvailability(id);

                return "redirect:../../";
            }
        }

        return "redirect:../login";
    }
}
