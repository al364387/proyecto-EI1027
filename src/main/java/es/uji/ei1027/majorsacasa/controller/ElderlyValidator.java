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
                    "Es necesario introducir un valor");
        }
        if (elderly.getSocialAssisId().trim().equals("")) {
            errors.rejectValue("socialAssisId", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getName().trim().equals("")) {
            errors.rejectValue("name", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getSurname().trim().equals("")) {
            errors.rejectValue("surname", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getEmail().trim().equals("")) {
            errors.rejectValue("email", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getBirthDate() == null) {
            errors.rejectValue("birthDate", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getPhoneNumber() == 0) {
            errors.rejectValue("phoneNumber", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getAddress().trim().equals("")) {
            errors.rejectValue("address", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getBankAccount().trim().equals("")) {
            errors.rejectValue("bankAccount", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getUsername().trim().equals("")) {
            errors.rejectValue("username", "obligado",
                    "Es necesario introducir un valor");
        }

        if (elderly.getPassword().trim().equals("")) {
            errors.rejectValue("password", "obligado",
                    "Es necesario introducir un valor");
        }
    }
}
