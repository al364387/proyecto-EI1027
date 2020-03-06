package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Hobby;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class HobbyRowMapper implements RowMapper<Hobby> {
    public Hobby mapRow(ResultSet rs, int rowNum) throws SQLException {
        Hobby interes = new Hobby();
        interes.setIdVolunteer(rs.getInt("idVolunteer"));
        interes.setType(rs.getString("type"));
        return interes;
    }
}