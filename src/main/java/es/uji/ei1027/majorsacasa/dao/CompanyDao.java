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
    public void addCompany(Company empresa) {
        jdbcTemplate.update("INSERT INTO Company VALUES(?, ?, ?, ?, ?, ?)",
                empresa.getCif(), empresa.getName(), empresa.getPhoneNumber(),
                empresa.getEmail(), empresa.getUsername(), empresa.getPassword());

        System.out.println(sendMail(empresa.getEmail(), empresa.getUsername(), empresa.getPassword(), empresa.getName()));
    }

    /* Esborra la empresa de la base de dades */
    public void deleteCompany(Company empresa) {
        jdbcTemplate.update("DELETE FROM Company WHERE cif = ?", empresa.getCif());
    }

    /* Actualitza els atributs de la Company */
    public void updateCompany(Company empresa) {
        jdbcTemplate.update("UPDATE Company SET phoneNumber = ?, email = ? WHERE cif = ?",
                empresa.getPhoneNumber(), empresa.getEmail(), empresa.getCif());
    }

    /* Obté el company amb el nom donat. Torna null si no existeix. */
    public Company getCompany(String cif) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Company WHERE cif = ?", new CompanyRowMapper(), cif);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté el nadador amb el nom donat. Torna null si no existeix. */
    public Company getUserCompany(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Company WHERE username = ?",
                    new CompanyRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els Company. Torna una llista buida si no n'hi ha cap. */
    public List<Company> getCompanies() {
        try {
            return jdbcTemplate.query("SELECT * FROM Company", new CompanyRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Company>();
        }
    }

    private String sendMail(String to, String user, String pass, String empresa){
        return  "De: cas@conselleria.com" + "\n" +
                "Para: " + to + "\n" +
                "Asunto: Alta en la web Majors a Casa" + "\n" +
                "Mensaje: \n" +
                "Se ha dado de Alta su empresa "+ empresa + " en la web Majors a Casa del CAS \n" +
                "Sus datos de acceso son: \n" +
                "Usuario: " + user + "\n" +
                "Contraseña " + pass;
    }
}
