package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class CompanyDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix la company a la base de dades */
    void addRequest(Company empresa) {
        jdbcTemplate.update("INSERT INTO Request VALUES(?, ?, ?, ?)",
                empresa.getCif(), empresa.getName(), empresa.getPhoneNumber(),
                empresa.getAddress());
    }

    /* Esborra la empresa de la base de dades */
    public void deleteRequest(Company empresa) {
        jdbcTemplate.update("DELETE FROM Company WHERE cif = ?", empresa.getCif());
    }

    /* Actualitza els atributs de la request */
    public void updateRequest(Company empresa) {
        jdbcTemplate.update("UPDATE Request SET phoneNumber = ?, address = ?", empresa.getPhoneNumber(), empresa.getAddress());
    }

    /* Obté el nadador amb el nom donat. Torna null si no existeix. */
    public Company getRequest(String cif) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Company WHERE cif = ?", new CompanyRowMapper(), cif);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els request. Torna una llista buida si no n'hi ha cap. */
    public List<Company> getNadadors() {
        try {
            return jdbcTemplate.query("SELECT * FROM Company", new CompanyRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Company>();
        }
    }
}