package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.SocialAssistant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialAssistantRowMapper implements RowMapper<SocialAssistant> {
    @Override
    public SocialAssistant mapRow(ResultSet resultSet, int i) throws SQLException {
        SocialAssistant socialAssistant = new SocialAssistant();

        socialAssistant.setDni(resultSet.getString("dni"));
        socialAssistant.setName(resultSet.getString("name"));
        socialAssistant.setSurname(resultSet.getString("surname"));
        socialAssistant.setEmail(resultSet.getString("email"));
        socialAssistant.setUsername(resultSet.getString("username"));
        socialAssistant.setPassword(resultSet.getString("password"));

        return socialAssistant;
    }
}
