package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.dao.ElderlyDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerAvailabilityDao;
import es.uji.ei1027.majorsacasa.dao.VolunteerDao;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerSvc implements VolunteerService{
    private VolunteerAvailabilityDao volunteerAvailabilityDao;
    private ElderlyDao elderlyDao;
    private VolunteerDao volunteerDao;

    @Autowired
    public void setVolunteerSvc(VolunteerAvailabilityDao volunteerAvailabilityDao, ElderlyDao elderlyDao,
                                VolunteerDao volunteerDao){
        this.volunteerAvailabilityDao = volunteerAvailabilityDao;
        this.elderlyDao = elderlyDao;
        this.volunteerDao = volunteerDao;
    }

    @Override
    public List<VolunteerAvailability> getListVolunteerAvailabilities(int id) {
        return volunteerAvailabilityDao.getVolunteerAvailabilities(id);
    }

    @Override
    public List<VolunteerAvailability> getListVolunteerAvailabilitiesOutElderly(int id) {
        return volunteerAvailabilityDao.getVolunteerAvailabilitiesOutElderly(id);
    }

    @Override
    public String getNameElderly(String id) {
        return elderlyDao.getElderly(id).getName() + " " + elderlyDao.getElderly(id).getSurname();
    }

    @Override
    public int getPhoneElderly(String id) {
        return elderlyDao.getElderly(id).getPhoneNumber();
    }

    @Override
    public String getAddressElderly(String id) {
        return elderlyDao.getElderly(id).getAddress();
    }

    @Override
    public String getNameVolunteer(int id) {
        return volunteerDao.getVolunteer(id).getName();
    }


}
