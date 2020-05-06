package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();

        admin.setUsername(resultSet.getString("username"));
        admin.setPassword(resultSet.getString("password"));

        return admin;
    }
}
