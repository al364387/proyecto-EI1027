package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Elderly;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ElderlyRowMapper implements RowMapper<Elderly> {

    @Override
    public Elderly mapRow(ResultSet rs, int i) throws SQLException {
        Elderly elderly = new Elderly();

        elderly.setSocialAsisNum(rs.getString("socialAsisNum"));
        elderly.setName(rs.getString("name"));
        elderly.setSurname(rs.getString("surname"));
        elderly.setEmail(rs.getString("email"));
        elderly.setDNI(rs.getString("dni"));
        elderly.setBirthDate(rs.getDate("birthDate"));
        elderly.setPhoneNumber(rs.getInt("phoneNumber"));
        elderly.setAddress(rs.getString("address"));
        elderly.setBankAccount(rs.getString("bankAccount"));
        elderly.setUsername(rs.getString("username"));
        elderly.setPassword(rs.getString("password"));

        return elderly;
    }
}
