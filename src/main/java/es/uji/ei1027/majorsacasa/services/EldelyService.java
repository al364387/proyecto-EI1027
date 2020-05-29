package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;

import java.util.List;

public interface EldelyService {
    List<Request> getRequestFormEldely(String dni);
    double getServicePrice(int contractId);
    List<VolunteerAvailability> getLeisureTime(String dni);
}
