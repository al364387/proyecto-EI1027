package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.Company;
import java.util.List;

public interface ContractService {
    List<Company> getCompanies();
    String getCompanyName(String CIF);
}
