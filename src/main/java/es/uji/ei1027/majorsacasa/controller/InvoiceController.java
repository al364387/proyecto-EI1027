package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.InvoiceDao;
import es.uji.ei1027.majorsacasa.model.Invoice;
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
@RequestMapping("/invoice")
//123

public class InvoiceController {
    private InvoiceDao invoiceDao;


    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @RequestMapping(value = "/delete/{id}")
    public String processDeleteInvoice(@PathVariable String invoiceNumber) {
        invoiceDao.deleteInvoice(invoiceNumber);
        return "redirect:../../list";
    }

    @RequestMapping("/list")
    public String listInvoice(Model model) {
        List<Invoice> l = invoiceDao.getInvoices();

        model.addAttribute("invoices", l);
        return "invoice/list";
    }

    @RequestMapping(value="/add")
    public String addInvoice(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("volunteer") Invoice invoice,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "invoice/add";
        invoiceDao.addVolunteer(invoice);
        return "redirect:list.html";
    }

}
