package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;

import java.util.List;

public interface VolunteerService {
    List<VolunteerAvailability> getListVolunteerAvailabilities(int id);
    List<VolunteerAvailability> getListVolunteerAvailabilitiesOutElderly(int id);
    String getNameElderly(String id);
    int getPhoneElderly(String id);
    String getNameVolunteer(int id);
}
