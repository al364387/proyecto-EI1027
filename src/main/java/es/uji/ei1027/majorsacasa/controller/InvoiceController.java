package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.dao.InvoiceDao;
import es.uji.ei1027.majorsacasa.dao.InvoiceLineDao;
import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Invoice;
import es.uji.ei1027.majorsacasa.model.InvoiceLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/invoice")

public class InvoiceController {
    private InvoiceDao invoiceDao;
    private InvoiceLineDao invoiceLineDao;


    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @RequestMapping(value = "/delete/{invoicenum}")
    public String processDeleteInvoice(@PathVariable String invoicenum) {
        invoiceDao.deleteInvoice(invoicenum);
        return "redirect:../list";
    }

    @RequestMapping("/list")
    public String listInvoice(Model model) {
        List<Invoice> l = invoiceDao.getInvoices();

        model.addAttribute("invoices", l);
        return "invoice/list";
    }


    //Intento 1, supongo
    @RequestMapping(value = "/facturaPDF/{invoicenum}")
    public String invoicePDF(Model model, HttpSession session, @PathVariable String invoicenum) {
        //Mirar si de pura casualidad funciona


        return "invoice/list";
    }


    @RequestMapping(value="/add")
    public String addInvoice(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("invoice") Invoice invoice,
                                   BindingResult bindingResult) {

        InvoiceValidator invoiceValidator = new InvoiceValidator();
        invoiceValidator.validate(invoice, bindingResult);

        if (bindingResult.hasErrors())
            return "invoice/add";
        invoiceDao.addInvoice(invoice);
        return "redirect:list";
    }

}
