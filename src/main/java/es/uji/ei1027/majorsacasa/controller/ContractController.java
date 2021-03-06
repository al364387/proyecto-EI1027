package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import es.uji.ei1027.majorsacasa.services.ContractService;
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
    private ContractService contractService;

    @Autowired
    public void setContractDao(ContractDao contractDao){
        this.contractDao = contractDao;
    }

    @Autowired
    public void setContractService(ContractService contractService){
        this.contractService = contractService;
    }

    @RequestMapping("/list")
    public String listContracts(HttpSession session, Model model){
        if (session.getAttribute("user") != null) {
            UserDetails user = (UserDetails) session.getAttribute("user");

            if (session.getAttribute("role").equals("Admin") && user.getUsername().equals("casManager")){
                model.addAttribute("contractService", contractService);
                model.addAttribute("contracts", contractDao.getContracts());
                return "contract/list";
            } else {
                return "index";
            }
        }

        model.addAttribute("user", new UserDetails());
        model.addAttribute("login", true);
        return "login";
    }

    @RequestMapping(value = "/add")
    public String addContract(HttpSession session, Model model){
        if (session.getAttribute("user") != null) {
            UserDetails user = (UserDetails) session.getAttribute("user");

            if (session.getAttribute("role").equals("Admin") && user.getUsername().equals("casManager")){
                model.addAttribute("contract", new Contract());
                model.addAttribute("contractService", contractService);
                return "contract/add";
            } else {
                return "index";
            }
        }

        model.addAttribute("login", true);
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("contract") Contract contract,
                                   BindingResult bindingResult, Model model){
        ContractValidator contractValidator = new ContractValidator();
        contractValidator.validate(contract, bindingResult);

        if (bindingResult.hasErrors()){
            model.addAttribute("contractService", contractService);
            return "contract/add";
        }

        contractDao.addContract(contract);
        return "redirect:list";
    }
}
