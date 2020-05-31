package es.uji.ei1027.majorsacasa.controller;


import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

        if (session.getAttribute("user") != null)
        {
            if (session.getAttribute("role").equals("Admin")){
                model.addAttribute("company", new Company());
                return "company/add";
            } else {
                return "index";
            }
        }

        model.addAttribute("login", true);
        model.addAttribute("user", new UserDetails());
        return "login";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("company") Company company,
                                   BindingResult bindingResult){

        CompanyValidator companyValidator = new CompanyValidator();
        companyValidator.validate(company,bindingResult);

        if (bindingResult.hasErrors()){
            return "company/add";
        }

        try {
            companyDao.addCompany(company);
        } catch (DuplicateKeyException e){
            throw new MajorsacasaException("con el CIF: " + company.getCif() +
                    " o con el usuario: " + company.getUsername(), "CPDuplicada");
        }

        return "redirect:list";
    }
    @RequestMapping("/list")
    public String listCompanies(Model model, HttpSession session){

        if (session.getAttribute("user") != null)
        {
            if (session.getAttribute("role").equals("Admin")){

                model.addAttribute("companies",companyDao.getCompanies());
                return "company/list";
            } else {
                return "index";
            }
        }

        model.addAttribute("login", true);
        model.addAttribute("user", new UserDetails());
        return "login";
    }



}
