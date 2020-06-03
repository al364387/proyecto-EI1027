package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class VolunteerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Volunteer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Volunteer volunteer = (Volunteer) o;

        if(volunteer.getName().trim().equals("")){
            errors.rejectValue("name", "Obligatorio", "Es necesario introducir el nombre");
        }

        if(volunteer.getSurname().trim().equals("")){
            errors.rejectValue("surname", "Obligatorio", "Es necesario introducir los apellidos");
        }

        if(volunteer.getBirthdate()==null){
            errors.rejectValue("birthdate", "Obligatorio", "Es necesario introducir la fecha de nacimiento");
        } else {
            if(volunteer.getBirthdate().getYear() + 18 > LocalDate.now().getYear()){
                errors.rejectValue("birthdate", "voluntario muy joven", "Es necesario tener 18 años para ser voluntario");
            }
        }

        if(volunteer.getPhonenumber()==0){
            errors.rejectValue("phonenumber", "Obligatorio", "Es necesario introducir el número de teléfono");
        }

        if(volunteer.getAddress().trim().equals("")){
            errors.rejectValue("address", "Obligatorio", "Es necesario introducir la dirección");
        }

        if(volunteer.getUsername().trim().equals("")){
            errors.rejectValue("username", "Obligatorio", "Es necesario introducir un nombre de usuario");
        }

        if(volunteer.getPassword().trim().equals("")){
            errors.rejectValue("password", "Obligatorio", "Es necesario introducir una contraseña");
        }





    }
}
