package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InvoiceValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Invoice.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Invoice invoice = (Invoice) o;

        if(invoice.getInvoicenum().trim().equals("")){
            errors.rejectValue("invoicenum", "obligatorio", "Ha de introducirse un valor");
        }
    }
}
