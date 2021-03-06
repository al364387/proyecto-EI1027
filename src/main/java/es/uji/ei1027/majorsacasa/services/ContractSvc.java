package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.dao.CompanyDao;
import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractSvc implements ContractService {
    CompanyDao companyDao;

    @Autowired
    public void setContractSvc(CompanyDao companyDao){
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    @Override
    public String getCompanyName(String CIF) {
        return companyDao.getCompany(CIF).getName();
    }
}
