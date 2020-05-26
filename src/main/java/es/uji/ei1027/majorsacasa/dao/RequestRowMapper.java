package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class RequestRowMapper implements RowMapper<Request> {
   public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
       Request peticion = new Request();
       peticion.setNumber(rs.getInt("number"));
       peticion.setState(rs.getString("state"));
       peticion.setStartDate(rs.getDate("startDate"));
       peticion.setEndDate(rs.getDate("endDate"));
       peticion.setTime(rs.getTime("time"));
       peticion.setCatering(rs.getBoolean("catering"));
       peticion.setNursing(rs.getBoolean("nursing"));
       peticion.setCleaning(rs.getBoolean("cleaning"));
       peticion.setDescription(rs.getString("description"));
       peticion.setElderlyId(rs.getString("elderlyId"));
       peticion.setContractId(rs.getInt("contractId"));
       return peticion;
   }
}
