package es.uji.ei1027.majorsacasa.dao;

import javax.sql.DataSource;

import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
        jdbcTemplate.update("INSERT INTO Volunteer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                createId(),
                volunteer.getName(), volunteer.getSurname(), volunteer.getBirthdate(), volunteer.getPhonenumber(), volunteer.getAddress(),
                null,
                volunteer.getUsername(), volunteer.getPassword(),
                null);
    }

    //Borrar Voluntario
    public void deleteVolunteer(int id) {
        jdbcTemplate.update("DELETE from Volunteer where id=?",
                id);
    }

    public void updateVolunteer(Volunteer volunteer) {
        jdbcTemplate.update("UPDATE Volunteer SET acceptDate =?, endDate =? WHERE id =?", volunteer.getAcceptDate() ,volunteer.getEndDate(), volunteer.getId());
    }

    //UPDATE Volunteer SET acceptDate='2020-05-14' WHERE id=2;
    public void updateVolunteerAcceptDate(int id, LocalDate date) {
        jdbcTemplate.update("UPDATE Volunteer SET acceptDate =? WHERE id =?", date, id);
    }

    public void updateVolunteerEndDate(int id, LocalDate date) {
        jdbcTemplate.update("UPDATE Volunteer SET endDate =? WHERE id =?", date, id);
    }


    public Volunteer getVolunteer(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Volunteer WHERE id =?", new VolunteerRowMapper(), id);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }
    public Volunteer getUserVolunteer(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Volunteer WHERE username = ?", new VolunteerRowMapper(), username);
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
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
            return new ArrayList<>();
        }
    }

    public int createId(){
        List<Volunteer> volunteers = getVolunteers();
        int id = volunteers.get(volunteers.size() - 1).getId() + 1;
        return id;
    }


}
