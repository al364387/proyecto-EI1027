package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;


public final class VolunteerRowMapper implements RowMapper<Volunteer> {

    public Volunteer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(rs.getInt("id"));
        volunteer.setName(rs.getString("name"));
        volunteer.setSurname(rs.getString("surname"));
        Date d = rs.getDate("birthdate");
        volunteer.setBirthdate(d!=null ? d.toLocalDate() : null);
        volunteer.setPhonenumber(rs.getInt("phoneNumber"));

        Date da = rs.getDate("acceptDate");
        volunteer.setAcceptDate(da!=null ? da.toLocalDate() : null);

        Date dat = rs.getDate("endDate");
        volunteer.setEndDate(dat!=null ? dat.toLocalDate() : null);

        volunteer.setAddress(rs.getString("address"));

        volunteer.setUsername(rs.getString("username"));
        volunteer.setPassword(rs.getString("password"));


        return volunteer;
    }


}

