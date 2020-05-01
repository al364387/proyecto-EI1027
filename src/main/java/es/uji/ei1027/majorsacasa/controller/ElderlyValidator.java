package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Elderly;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ElderlyValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Elderly.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Elderly elderly = (Elderly) o;

        if (elderly.getDNI().trim().equals("")) {
            errors.rejectValue("DNI", "obligado",
                    "Es necsario introducir un valor");
        }
    }
}
