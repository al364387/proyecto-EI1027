package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/request")
public class RequestController {
    private RequestDao requestDao;
    private ContractDao contractDao;

    @Autowired
    public void setRequestDao(RequestDao requestDao, ContractDao contractDao){
        this.requestDao=requestDao;
        this.contractDao=contractDao;
    }

    @RequestMapping("/list")
    public String listCompanies(Model model){
        model.addAttribute("requests",requestDao.getRequests());
        return "request/list";
    }

    @RequestMapping(value = "/add")
    public String addRequest(Model model){
        model.addAttribute("request", new Request());
        model.addAttribute("listContracts", contractDao.getContracts());
        return "request/add";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("request") Request request,
                                   BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "request/add";
        }

        requestDao.addRequest(request);
        return "redirect:list";
    }

}