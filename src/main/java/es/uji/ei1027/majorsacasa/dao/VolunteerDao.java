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
        jdbcTemplate.update("INSERT INTO Volunteer (name, surname, birthDate, phoneNumber, address, acceptDate, " +
                        "userName, password, endDate, state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
                volunteer.getName(), volunteer.getSurname(), volunteer.getBirthdate(), volunteer.getPhonenumber(),
                volunteer.getAddress(), volunteer.getAcceptDate(), volunteer.getUsername(), volunteer.getPassword(),
                volunteer.getEndDate(), volunteer.getState());
    }

    //Borrar Voluntario
    public void deleteVolunteer(int id) {
        jdbcTemplate.update("DELETE from Volunteer where id=?",
                id);
    }

    public void updateVolunteer(Volunteer volunteer, int id) {
        jdbcTemplate.update("UPDATE Volunteer SET name = ?, surname = ?, phonenumber = ?, address = ?, " +
                        "password = ?  WHERE id = ?",
                volunteer.getName(), volunteer.getSurname(), volunteer.getPhonenumber(), volunteer.getAddress(),
                volunteer.getPassword(), id);
    }

    //Cambia estado y fechas
    public void updateVolunteerAccept(int id, LocalDate date) {
        jdbcTemplate.update("UPDATE Volunteer SET acceptDate =?, endDate=null, state='Aceptado' WHERE id =?",
                date, id);
    }

    public void cancelVolunteer(int id) {

        LocalDate endDate=LocalDate.now();

        //TODO: Cambiar el state al que toque
        jdbcTemplate.update("UPDATE Volunteer SET endDate =? WHERE id =?", endDate, id);
    }

    public void updateVolunteerReject(int id, String state) {
        jdbcTemplate.update("UPDATE Volunteer SET state=? WHERE id =?", state, id);
    }


    public Volunteer getVolunteer(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Volunteer WHERE id =?", new VolunteerRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Volunteer getUserVolunteer(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Volunteer WHERE username = ?",
                    new VolunteerRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Listar Voluntarios
    public List<Volunteer> getVolunteers() {
        try {
            List<Volunteer> c = jdbcTemplate.query("SELECT * from Volunteer WHERE state= 'Aceptado'",
                    new VolunteerRowMapper());
            return c;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Volunteer> getVolunteersPendientes() {
        try {
            List<Volunteer> c = jdbcTemplate.query("SELECT * from Volunteer WHERE state= 'Pendiente'",
                    new VolunteerRowMapper());
            return c;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<Volunteer> getVolunteersRechazados() {
        try {
            List<Volunteer> c = jdbcTemplate.query("SELECT * from Volunteer WHERE state='Rechazado'",
                    new VolunteerRowMapper());
            return c;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }


}
