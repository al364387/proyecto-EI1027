package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerAvailabilityDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Añade la disponibilidad del voluntario
    public void addVolunteerAvailability(VolunteerAvailability volunteerAvailability, int idVolunteer){
        jdbcTemplate.update("INSERT INTO VolunteerAvailability (startTime, endTime, monday, tuesday, " +
                        "wednesday, thursday, friday, saturday, sunday, hobby, idVolunteer, dniElderly) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                volunteerAvailability.getStartTime(), volunteerAvailability.getEndTime(), volunteerAvailability.isMonday(),
                volunteerAvailability.isTuesday(), volunteerAvailability.isWednesday(), volunteerAvailability.isThursday(),
                volunteerAvailability.isFriday(), volunteerAvailability.isSaturday(), volunteerAvailability.isSunday(),
                volunteerAvailability.getHobby(), idVolunteer, volunteerAvailability.getDniEderly());
    }

    // Actualiza la disponibilidad del voluntario, a excepción de las claves primarias.
    public void updateVolunteerAvailability(VolunteerAvailability volunteerAvailability) {
        jdbcTemplate.update("UPDATE VolunteerAvailability " +
                "SET startTime = ?, endTime = ?, monday = ?, tuesday = ?, wednesday = ?, thursday= ?, friday = ?," +
                " saturday = ?, sunday = ? WHERE idVolunteer = ? AND dniElderly = ?",
                volunteerAvailability.getStartTime(), volunteerAvailability.getEndTime(), volunteerAvailability.isMonday(),
                volunteerAvailability.isTuesday(), volunteerAvailability.isWednesday(), volunteerAvailability.isThursday(),
                volunteerAvailability.isFriday(), volunteerAvailability.isSaturday(), volunteerAvailability.isSunday(),
                volunteerAvailability.getIdVolunteer(), volunteerAvailability.getDniEderly());
    }

    // Asocia la disponibilidad del voluntario a una persona mayor.
    public void updateVolunteerAvailabilityAddElderly(int id, String dniEderly) {
        jdbcTemplate.update("UPDATE VolunteerAvailability SET dniElderly = ? WHERE id = ?",
                dniEderly, id);
    }


    // Borra la disponibilidad del voluntario de la bbdd
    public void deleteVolunteerAvailability(int volunteer, String dniElderly){
        jdbcTemplate.update("DELETE FROM VolunteerAvailability WHERE idVolunteer = ? AND dniElderly = ?",
                volunteer, dniElderly);
    }

    // Muestra la disponibilidad de un voluntario. Devuelve nulo si no existe.
    public VolunteerAvailability getVolunteerAvailability(int volunteer){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM VolunteerAvailability WHERE idVolunteer = ?",
                    new VolunteerAvailabilityRowMapper(), volunteer);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Muestra la disponibilidad de un voluntario asociado a una persona mayor. Devuelve nulo si no existe.
    public VolunteerAvailability getVolunteerAvailabilityWithElderly(int volunteer, String dniElderly){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM VolunteerAvailability WHERE idVolunteer = ? " +
                    "AND dniElderly = ?", new VolunteerAvailabilityRowMapper(), volunteer, dniElderly);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Muestra la disponibilidad de todos los voluntarios asociados a una persona mayor. Devuelve nulo si no existe.
    public  List<VolunteerAvailability> getVolunteersAvailabilityFromElderly(String dniElderly){
        try{
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE dniElderly = ?",
                    new VolunteerAvailabilityRowMapper(), dniElderly);
        } catch (EmptyResultDataAccessException e){
            return new ArrayList<VolunteerAvailability>();
        }
    }

    // Muestra todas las disponibilidades de los voluntarios que no esten asociados a una persona mayor.
    // Devuelve una lista vacia si no hay ninguna.
    public List<VolunteerAvailability> getAllVolunteerAvailabilities(){
        try{
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE dniElderly = null", new VolunteerAvailabilityRowMapper());
        } catch (EmptyResultDataAccessException e){
            return new ArrayList<VolunteerAvailability>();
        }
    }
}
