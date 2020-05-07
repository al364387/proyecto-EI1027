package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CompanyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Company company = (Company) o;

        if (company.getName().trim().equals("")){
            errors.rejectValue("name","Obligatorio","Es necesario introducir un nombre");
        }
        if (company.getCif().trim().equals("")){
            errors.rejectValue("cif","Obligatorio","Es necesario introducir un CIF");
        }
        if (company.getPhoneNumber()==0){
            errors.rejectValue("phoneNumber","Obligatorio","Es necesario introducir un teléfono");
        }
        if (company.getEmail().trim().equals("")){
            errors.rejectValue("email","Obligatorio","Es necesario introducir un correo electrónico");
        }
        if (company.getUsername().trim().equals("")){
            errors.rejectValue("username","Obligatorio","Es necesario introducir un nombre de usuario");
        }
        if (company.getPassword().trim().equals("")){
            errors.rejectValue("password","Obligatorio","Es necesario introducir una contraseña");
        }
    }
}
