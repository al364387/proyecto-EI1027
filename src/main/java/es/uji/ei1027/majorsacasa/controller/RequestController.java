package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Elderly;
import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.Admin;
import es.uji.ei1027.majorsacasa.model.UserDetails;
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
@RequestMapping("/request")
public class RequestController {
    private RequestDao requestDao;
    private ContractDao contractDao;
    private ElderlyDao elderlyDao;

    @Autowired
    public void setRequestDao(RequestDao requestDao, ContractDao contractDao, ElderlyDao elderlyDao) {
        this.requestDao = requestDao;
        this.contractDao = contractDao;
        this.elderlyDao = elderlyDao;
    }

    @RequestMapping("/list")
    public String listResquest(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Admin")) {
                model.addAttribute("isAdmin", true);
                model.addAttribute("requests", requestDao.getRequests());
                return "request/list";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Admin());
        model.addAttribute("login", true);
        return "login";
    }

    @RequestMapping(value = "/add")
    public String addRequest(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {
                model.addAttribute("request", new Request());
                model.addAttribute("listContracts", contractDao.getContracts());
                model.addAttribute("dni", session.getAttribute("dni"));

                return "request/add";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Elderly());
        model.addAttribute("login", true);

        return "login";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("request") Request request,
                                   BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("listContracts", contractDao.getContracts());
            return "request/add";
        }

        requestDao.addRequest(request, (String) session.getAttribute("dni"), contractDao);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{number}/{estado}", method = RequestMethod.GET)
    public String editRequestStatus(@PathVariable String number, @PathVariable String estado, Model model,
                                    HttpSession session) {

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Admin")) {
                requestDao.updateRequestStatus(Integer.parseInt(number), estado);
                return "redirect:../../list";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Admin());
        model.addAttribute("login", true);
        return "login";
    }

}