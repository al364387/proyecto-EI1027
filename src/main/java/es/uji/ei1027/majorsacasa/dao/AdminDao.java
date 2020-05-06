package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AdminDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Muestra los datos del admin. Devuelve nulo si no existe
    public Admin getUserAdmin(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Admin WHERE username = ?", new AdminRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
