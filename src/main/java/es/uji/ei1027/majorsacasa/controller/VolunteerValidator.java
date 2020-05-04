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
            errors.rejectValue("name", "obligatorio", "Ha de introducirse un valor");
        }

        if(volunteer.getSurname().trim().equals("")){
            errors.rejectValue("surname", "obligatorio", "Ha de introducirse un valor");
        }

        if(volunteer.getUsername().trim().equals("")){
            errors.rejectValue("username", "obligatorio", "Ha de introducirse un valor");
        }

        if(volunteer.getPassword().trim().equals("")){
            errors.rejectValue("username", "obligatorio", "Ha de introducirse un valor");
        }
/*
        if((volunteer.getBirthdate().getYear() + 18) < LocalDate.now().getYear()){
            errors.rejectValue("birthdate", "voluntario muy joven", "Es necesario tener 18 años para ser voluntario");
        }
*/

    }
}
