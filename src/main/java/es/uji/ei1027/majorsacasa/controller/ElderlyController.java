package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.dao.SocialAssistantDao;
import es.uji.ei1027.majorsacasa.model.Elderly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/elderly")
public class ElderlyController {
    private ElderlyDao elderlyDao;
    private SocialAssistantDao socialAssistantDao;

    @Autowired
    public void setElderlyDao(ElderlyDao elderlyDao) {
        this.elderlyDao = elderlyDao;
    }

    @RequestMapping("/list")
    public String listElderlies(Model model) {
        model.addAttribute("elderlies", elderlyDao.getElderlies());
        return "elderly/list";
    }

    @RequestMapping(value = "/add")
    public String addElderly(Model model) {
        model.addAttribute("elderly", new Elderly());
        model.addAttribute("register", true);
        return "elderly/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("elderly") Elderly elderly,
                                   BindingResult bindingResult,  Model model) {

        ElderlyValidator elderlyValidator = new ElderlyValidator();
        elderlyValidator.validate(elderly, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("register", true);
            return "elderly/add";
        }

        try {
            elderlyDao.addElderly(elderly, socialAssistantDao.getSocialAssistantID());
        } catch (NullPointerException e){
            throw new MajorsacasaException(
                "con el DNI: " + elderly.getDNI() +
                " o con el usuario: " + elderly.getUsername(),
                "CPduplicada");
        }

        return "redirect:list";
    }

    @RequestMapping(value = "/update/{dni}", method = RequestMethod.GET)
    public String editElderly(Model model, @PathVariable String dni) {
        model.addAttribute("elderly", elderlyDao.getElderly(dni));
        return "elderly/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("elderly") Elderly elderly,
                                      BindingResult bindingResult) {

        ElderlyValidator elderlyValidator = new ElderlyValidator();
        elderlyValidator.validate(elderly, bindingResult);

        if (bindingResult.hasErrors()){
            return "elderly/update";
        }
        elderlyDao.upadateElderly(elderly);
        return "redirect:list";
    }

    @RequestMapping(value = "delete/{dni}")
    public String processDelete(@PathVariable String dni){
        try{
            elderlyDao.deleteElderly(dni);
        }catch (Exception e){
            throw new MajorsacasaException(
                "No se puede borrar el usuario con DNI: " + dni +
                " si aun tiene servicios solicitados",
                "CPConServicios");
        }

        return "redirect:../list";
    }
}
