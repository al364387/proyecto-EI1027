package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.dao.ContractDao;
import es.uji.ei1027.majorsacasa.dao.RequestDao;
import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlySvc implements EldelyService{
    @Autowired
    RequestDao requestDao;

    @Autowired
    ContractDao contractDao;


    @Override
    public List<Request> getRequestFormEldely(String dni) {
        return requestDao.getRequestsFromEldely(dni);
    }

    @Override
    public double getServicePrice(int contractId) {
        return contractDao.getContract(contractId).getPrice();
    }
}
