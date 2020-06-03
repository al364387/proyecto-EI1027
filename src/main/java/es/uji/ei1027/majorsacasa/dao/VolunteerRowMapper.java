package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public final class VolunteerRowMapper implements RowMapper<Volunteer> {

    public Volunteer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Volunteer volunteer = new Volunteer();

        volunteer.setId(Integer.parseInt(rs.getString("id")));
        volunteer.setName(rs.getString("name"));
        volunteer.setSurname(rs.getString("surname"));
        volunteer.setBirthdate(rs.getObject("birthdate", LocalDate.class));
        volunteer.setPhonenumber(rs.getInt("phoneNumber"));
        volunteer.setAcceptDate(rs.getObject("acceptDate", LocalDate.class));
        volunteer.setEndDate(rs.getObject("endDate", LocalDate.class));
        volunteer.setAddress(rs.getString("address"));
        volunteer.setUsername(rs.getString("username"));
        volunteer.setPassword(rs.getString("password"));
        volunteer.setState(rs.getString("state"));

        return volunteer;
    }
}

