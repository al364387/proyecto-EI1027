package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public final class VolunteerRowMapper implements RowMapper<Volunteer> {

    public Volunteer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(rs.getString("name"));
        volunteer.setSurname(rs.getString("surname"));
        Date d = rs.getDate("birthdate");
        volunteer.setBirthdate(d!=null ? d.toLocalDate() : null); //Averiguar como sacar fechas
        volunteer.setPhonenumber(rs.getInt("phoneNumber"));
        Date d2 = rs.getDate("acceptDate");
        volunteer.setBirthdate(d2!=null ? d2.toLocalDate() : null);
        volunteer.setUsername(rs.getString("username"));
        volunteer.setPassword(rs.getString("password"));
        Date d3 = rs.getDate("endDate");
        volunteer.setBirthdate(d3!=null ? d3.toLocalDate() : null);
        return volunteer;
    }


}

