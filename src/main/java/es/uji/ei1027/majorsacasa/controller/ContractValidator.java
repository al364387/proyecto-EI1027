package es.uji.ei1027.majorsacasa.controller;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ContractValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Contract.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Contract contract = (Contract) o;

        if (contract.getAuxService() == null) {
            errors.rejectValue("auxService", "obligado",
                    "Es necesario elegir una opción");
        }

        if (contract.getPrice() == 0) {
            errors.rejectValue("price", "obligado",
                    "Es necesario introducir un precio");
        } else{
            if (contract.getPrice() < 0) {
                errors.rejectValue("price", "obligado",
                        "Es necesario introducir un precio mayor a 0");
            }
        }

        if (contract.getServiceNumber() == 0) {
            errors.rejectValue("serviceNumber", "obligado",
                    "Es necesario introducir un número de servicios");
        } else{
            if (contract.getServiceNumber() < 0) {
                errors.rejectValue("serviceNumber", "obligado",
                        "Es necesario introducir un número de servicios a 0");
            }
        }

        if (contract.getStartDate() == null) {
            errors.rejectValue("startDate", "obligado",
                    "Es necesario introducir una fecha");
        }

        if (contract.getEndDate() == null) {
            errors.rejectValue("endDate", "obligado",
                    "Es necesario introducir una fecha");
        }
    }
}
