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
                    "Es necesario introducir el dni");
        }

        if (elderly.getName().trim().equals("")) {
            errors.rejectValue("name", "obligado",
                    "Es necesario introducir el nombre");
        }

        if (elderly.getSurname().trim().equals("")) {
            errors.rejectValue("surname", "obligado",
                    "Es necesario introducir los apellidos");
        }

        if (elderly.getEmail().trim().equals("")) {
            errors.rejectValue("email", "obligado",
                    "Es necesario introducir el email");
        }

        if (elderly.getBirthDate() == null) {
            errors.rejectValue("birthDate", "obligado",
                    "Es necesario introducir la fecha de nacimiento");
        }

        if (elderly.getPhoneNumber() == 0) {
            errors.rejectValue("phoneNumber", "obligado",
                    "Es necesario introducir el número de teléfono");
        }

        if (elderly.getAddress().trim().equals("")) {
            errors.rejectValue("address", "obligado",
                    "Es necesario introducir la dirección");
        }

        if (elderly.getBankAccount().trim().equals("")) {
            errors.rejectValue("bankAccount", "obligado",
                    "Es necesario introducir el número de cuenta");
        }

        if (elderly.getUsername().trim().equals("")) {
            errors.rejectValue("username", "obligado",
                    "Es necesario introducir un nombre de usuario");
        }

        if (elderly.getPassword().trim().equals("")) {
            errors.rejectValue("password", "obligado",
                    "Es necesario introducir una contraseña");
        }
    }
}
