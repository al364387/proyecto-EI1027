package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/contract")
public class ContractController {
    private ContractDao contractDao;

    @Autowired
    public void setContractDao(ContractDao contractDao){
        this.contractDao = contractDao;
    }

    @RequestMapping("/list")
    public String listContracts(HttpSession session, Model model){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            model.addAttribute("login", true);
            return "login";
        }
        model.addAttribute("isAdmin", true);
        model.addAttribute("contracts", contractDao.getContracts());
        return "contract/list";
    }

    @RequestMapping(value = "/add")
    public String addContract(HttpSession session, Model model){
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            return "login";
        }
        model.addAttribute("isAdmin", true);
        model.addAttribute("contract", new Contract());
        return "contract/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("contract") Contract contract,
                                   BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "contract/add";
        }

        contractDao.addContract(contract);
        return "redirect:list";
    }
}
