package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.InvoiceLineDao;
import es.uji.ei1027.majorsacasa.model.InvoiceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/invoiceLine")
public class InvoiceLineController {
    private InvoiceLineDao invoiceLineDao;

    @Autowired
    public void setInvoiceDao(InvoiceLineDao invoiceLineDao) {
        this.invoiceLineDao = invoiceLineDao;
    }

    @RequestMapping(value = "/delete/{invoiceNumber}")
    public String processDeleteInvoice(@PathVariable int invoiceNumber) {
        invoiceLineDao.deleteInvoiceLine(invoiceNumber);
        return "redirect:../list";
    }

    @RequestMapping("/list")
    public String listInvoice(Model model, String invoiceNumId) {
        List<InvoiceLine> l = invoiceLineDao.getInvoiceLines(invoiceNumId);

        model.addAttribute("invoiceLines", l);
        return "invoiceLine/list";
    }

    @RequestMapping(value="/add")
    public String addInvoice(Model model) {
        model.addAttribute("invoiceLine", new InvoiceLine());
        return "invoiceLine/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("invoiceLine") InvoiceLine invoiceLine,
                                   BindingResult bindingResult) {
        InvoiceLineValidator invoiceLineValidator = new InvoiceLineValidator();
        invoiceLineValidator.validate(invoiceLine, bindingResult);
        if (bindingResult.hasErrors())
            return "invoiceLine/add";
        invoiceLineDao.addInvoiceLine(invoiceLine);
        return "redirect:list";
    }

    @RequestMapping(value="/update/{number}", method = RequestMethod.GET)
    public String editInvoiceLine(Model model, @PathVariable int invoiceLine) {
        model.addAttribute("invoiceLine", invoiceLineDao.getInvoiceLine(invoiceLine));
        return "invoiceLine/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(
            @ModelAttribute("invoiceLine") InvoiceLine invoiceLine,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "invoiceLine/update";
        invoiceLineDao.updateInvoiceLine(invoiceLine);
        return "redirect:list";
    }


}
