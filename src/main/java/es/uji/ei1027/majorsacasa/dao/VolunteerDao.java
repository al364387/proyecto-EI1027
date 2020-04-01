package es.uji.ei1027.majorsacasa.dao;

import javax.sql.DataSource;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Añadir Voluntario
    public void addVolunteer(Volunteer volunteer) {
        jdbcTemplate.update("INSERT INTO Volunteer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                volunteer.getName(), volunteer.getSurname(), volunteer.getBirthdate(), volunteer.getPhonenumber(), volunteer.getAddress(),
                volunteer.getAcceptDate(), volunteer.getUsername(), volunteer.getPassword(), volunteer.getEndDate());
    }

    //Borrar Voluntario
    public void deleteVolunteer(int id) {
        jdbcTemplate.update("DELETE from Volunteer where id=?",
                id);
    }

    //Listar Voluntarios
    public List<Volunteer> getVolunteers() {
        try {
            List<Volunteer> c = jdbcTemplate.query("SELECT * from Volunteer",
                    new VolunteerRowMapper());
            System.out.println("template: " + c);
            return c;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("sin voluntarios: ");
            return new ArrayList<Volunteer>();
        }
    }
}
