package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public final class RequestRowMapper implements RowMapper<Request> {
   public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
       Request peticion = new Request();
       peticion.setNumber(rs.getInt("number"));
       peticion.setState(rs.getString("state"));
       peticion.setStartDate(rs.getObject("startDate", LocalDate.class));
       peticion.setEndDate(rs.getObject("endDate", LocalDate.class));
       peticion.setTime(rs.getObject("time", LocalTime.class));
       peticion.setCatering(rs.getBoolean("catering"));
       peticion.setNursing(rs.getBoolean("nursing"));
       peticion.setCleaning(rs.getBoolean("cleaning"));
       peticion.setDescription(rs.getString("description"));
       peticion.setElderlyDNI(rs.getString("elderlyId"));
       peticion.setContractId(rs.getInt("contractId"));
       return peticion;
   }
}
