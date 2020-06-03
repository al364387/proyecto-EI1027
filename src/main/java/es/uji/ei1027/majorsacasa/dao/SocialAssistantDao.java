package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.SocialAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SocialAssistantDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Muestra el ID del asistente social
    public SocialAssistant getSocialAssistantID(){
        return jdbcTemplate.queryForObject("SELECT * FROM SocialAssistant", new SocialAssistantRowMapper());
    }
}
