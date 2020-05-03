package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.InvoiceLine;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InvoiceLineValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return InvoiceLine.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        InvoiceLine invoiceLine = (InvoiceLine) o;
        if(invoiceLine.getInvoiceNumId().trim().equals("")){
            errors.rejectValue("invoiceNumId", "obligatorio", "Ha de introducirse un valor");
        }

        if(invoiceLine.getNumber() < 0){
            errors.rejectValue("number", "obligatorio", "Ha de introducirse un valor");
        }


    }
}
