package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class RequestController {
    private RequestDao requestDao;

    @Autowired
    public void setRequestDao(RequestDao requestDao){
        this.requestDao=requestDao;
    }

    @RequestMapping("/list")
    public String listCompanies(Model model){
        model.addAttribute("requests",requestDao.getRequests());
        return "request/list";
    }

}