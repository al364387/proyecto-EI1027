package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;

import java.util.List;

public interface ElderlyService {
    List<Request> getRequestFormEldely(String dni);
    double getServicePrice(int contractId);
    List<VolunteerAvailability> getLeisureTime(String dni);
    String getNameVolunteer(int id);
    int getPhoneVolunteer(int id);
}
