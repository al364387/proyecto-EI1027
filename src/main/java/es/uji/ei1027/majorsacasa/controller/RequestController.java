package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Elderly;
import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.Admin;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import es.uji.ei1027.majorsacasa.services.ElderlyService;
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
    private ElderlyService elderlyService;

    @Autowired
    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Autowired
    public void setContractDao(ContractDao contractDao){
        this.contractDao = contractDao;
    }

    @Autowired
    public void setElderlyService(ElderlyService elderlyService){
        this.elderlyService = elderlyService;
    }

    @RequestMapping("/list")
    public String listResquest(HttpSession session, Model model) {
        if (session.getAttribute("user") != null) {
            UserDetails user = (UserDetails) session.getAttribute("user");

            if (session.getAttribute("role").equals("Admin") && user.getUsername().equals("casCommitee")){
                session.setAttribute("elderlyService", elderlyService);
                session.setAttribute("requests", requestDao.getRequests());
                return "request/list";

            } else if(session.getAttribute("role").equals("Company")){
                //No se todavia si funciona, es una idea
                model.addAttribute("requestD", requestDao);
            }else{
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

        ElderlyService elderlyService = (ElderlyService) session.getAttribute("elderlyService");
        String dni = (String) session.getAttribute("dni");

        session.setAttribute("requests", elderlyService.getRequestFormEldely(dni));

        requestDao.addRequest(request, dni, contractDao);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{number}/{estado}", method = RequestMethod.GET)
    public String editRequestStatus(@PathVariable int number, @PathVariable String estado, Model model,
                                    HttpSession session) {

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Admin")) {
                session.setAttribute("requests", requestDao.getRequests());
                requestDao.updateRequestStatus(number, estado);
                return "redirect:../../list";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new Admin());
        model.addAttribute("login", true);
        return "login";
    }

    @RequestMapping(value = "/info/{number}", method = RequestMethod.GET)
    public String infoRequest(@PathVariable int number, HttpSession session){
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                session.setAttribute("requestElderly", requestDao.getRequest(number));

                return "request/info";
            }
        }

        return  "redirect:../../login";
    }

    @RequestMapping(value = "/cancel/{number}", method = RequestMethod.GET)
    public String cancelRequest(@PathVariable int number, HttpSession session){

        if (session.getAttribute("user") != null) {
            if (session.getAttribute("role").equals("Elderly")) {

                requestDao.cancelRequest(number);
                ElderlyService elderlyService = (ElderlyService) session.getAttribute("elderlyService");
                session.setAttribute("requests", elderlyService.getRequestFormEldely((String) session.getAttribute("dni")));

                return "redirect:../../";
            }
        }

        return  "redirect:../../login";
    }

}