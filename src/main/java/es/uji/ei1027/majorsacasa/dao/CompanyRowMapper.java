package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
        Company empresa = new Company();

        empresa.setCif(rs.getString("cif"));
        empresa.setName(rs.getString("name"));
        empresa.setPhoneNumber(rs.getInt("phoneNumber"));
        empresa.setEmail(rs.getString("email"));

        return empresa;
    }
}