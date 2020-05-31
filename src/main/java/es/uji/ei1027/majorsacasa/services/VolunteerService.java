package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;

import java.util.List;

public interface VolunteerService {
    public List<VolunteerAvailability> getListVolunteerAvailabilities(int id);
    String getNameElderly(String id);
    int getPhoneElderly(String id);
}
