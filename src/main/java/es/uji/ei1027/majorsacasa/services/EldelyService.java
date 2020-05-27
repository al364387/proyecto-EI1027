package es.uji.ei1027.majorsacasa.services;

import es.uji.ei1027.majorsacasa.model.Request;

import java.util.List;

public interface EldelyService {
    public List<Request> getRequestFormEldely(String dni);
    public double getServicePrice(int contractId);
}
