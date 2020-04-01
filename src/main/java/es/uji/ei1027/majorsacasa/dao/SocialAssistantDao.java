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

    // Añade al asistente social a la bbdd
    public void addSocialAssistant(SocialAssistant socialAssistant){
        jdbcTemplate.update("INSERT INTO SocialAssistant VALUES( ?, ?, ?, ?, ?, ?)",
                socialAssistant.getDni(), socialAssistant.getName(),
                socialAssistant.getSurname(), socialAssistant.getEmail(),
                socialAssistant.getUsername(), socialAssistant.getPassword());
    }

    // Actualiza los datos del asistente social
    public void updateSocialAssistant(SocialAssistant socialAssistant){
        jdbcTemplate.update("UPDATE SocialAssistant SET name = ?, surname = ?, email = ?, username = ?, " +
                "password = ? WHERE dni = ?",
                socialAssistant.getName(), socialAssistant.getSurname(), socialAssistant.getEmail(),
                socialAssistant.getUsername(), socialAssistant.getPassword());
    }

    // Borra el asistente social de la bbdd
    public void deleteElderly(String dni){
        jdbcTemplate.update("DELETE FROM SocialAssistant WHERE dni = ?", dni);
    }

    // Muestra los datos de un asistente social. Devuelve nulo si no existe
    public SocialAssistant getSocialAssistant(String dni){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM SocialAssistant WHERE dni = ?",
                    new SocialAssistantRowMapper(), dni);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Muestra todos los datos de los asistentes sociales
    public List<SocialAssistant> getSocialAssistant(){
        try {
            return jdbcTemplate.query("SELECT * FROM SocialAssistant ", new SocialAssistantRowMapper());
        } catch (EmptyResultDataAccessException e){
            return new ArrayList<SocialAssistant>();
        }
    }

}
