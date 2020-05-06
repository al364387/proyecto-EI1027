package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyDao companyDao;

    @Autowired
    public void setCompanyDao(CompanyDao companyDao){
        this.companyDao=companyDao;
    }

    @RequestMapping(value = "/add")
    public String addCompany(HttpSession session, Model model){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("company", new Company());
        model.addAttribute("listCompanies", companyDao.getCompanies());
        return "company/add";

    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "company/add";
        }

        companyDao.addCompany(company);
        return "redirect:list";
    }
    @RequestMapping("/list")
    public String listCompanies(Model model){
        model.addAttribute("companies",companyDao.getCompanies());
        return "company/list";
    }



}
