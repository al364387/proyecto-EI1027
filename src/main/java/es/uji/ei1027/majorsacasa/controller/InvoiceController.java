package es.uji.ei1027.majorsacasa.controller;

import com.lowagie.text.DocumentException;
import es.uji.ei1027.majorsacasa.PdfGenaratorUtil;
import es.uji.ei1027.majorsacasa.dao.InvoiceDao;
import es.uji.ei1027.majorsacasa.dao.InvoiceLineDao;
import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/invoice")

public class InvoiceController {
    private InvoiceDao invoiceDao;
    private InvoiceLineDao invoiceLineDao;

    @Autowired
    PdfGenaratorUtil pdfGenaratorUtil;
    public void setPdfGenaratorUtil(PdfGenaratorUtil pdfGenaratorUtil) {
        this.pdfGenaratorUtil = pdfGenaratorUtil;
    }

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
    @RequestMapping(value="/templatePDF")
    public String createPdf(Model model) throws Exception {
        Map<String,String> data = new HashMap<String,String>();
        //El mapa que ha de pasarle tiene que tener las lineas de factura
        data.put("name","James");
        //Estoy en blanco, lo siento
        pdfGenaratorUtil.createPdf("invoice/templatePDF", data);
        return "redirect:list";

    }
}
