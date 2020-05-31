package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.dao.*;
import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlySvc implements ElderlyService {
    RequestDao requestDao;
    ContractDao contractDao;
    VolunteerAvailabilityDao volunteerAvailabilityDao;
    VolunteerDao volunteerDao;
    ElderlyDao elderlyDao;

    @Autowired
    public void setElderlySvc(RequestDao requestDao, ContractDao contractDao,
                              VolunteerAvailabilityDao volunteerAvailabilityDao, VolunteerDao volunteerDao,
                              ElderlyDao elderlyDao) {
        this.requestDao = requestDao;
        this.contractDao = contractDao;
        this.volunteerAvailabilityDao = volunteerAvailabilityDao;
        this.volunteerDao = volunteerDao;
        this.elderlyDao = elderlyDao;
    }

    @Override
    public List<Request> getRequestFormEldely(String dni) {
        return requestDao.getRequestsFromEldely(dni);
    }

    @Override
    public double getServicePrice(int contractId) {
        return contractDao.getContract(contractId).getPrice();
    }

    @Override
    public List<VolunteerAvailability> getLeisureTime(String dni) {
        return volunteerAvailabilityDao.getVolunteersAvailabilityFromElderly(dni);
    }

    @Override
    public String getNameVolunteer(int id) {
        return volunteerDao.getVolunteer(id).getName() + " " + volunteerDao.getVolunteer(id).getSurname();
    }

    @Override
    public int getPhoneVolunteer(int id) {
        return volunteerDao.getVolunteer(id).getPhonenumber();
    }

    @Override
    public String getNameElderly(String dni) {
        return elderlyDao.getElderly(dni).getName() + " " + elderlyDao.getElderly(dni).getSurname();
    }
}
