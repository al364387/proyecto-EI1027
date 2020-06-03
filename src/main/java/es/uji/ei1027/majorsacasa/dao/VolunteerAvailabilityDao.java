package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerAvailabilityDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Añade la disponibilidad del voluntario
    public void addVolunteerAvailability(VolunteerAvailability volunteerAvailability, int idVolunteer) {
        jdbcTemplate.update("INSERT INTO VolunteerAvailability (startTime, endTime, monday, tuesday, " +
                        "wednesday, thursday, friday, saturday, sunday, hobby, endDate, idVolunteer, dniElderly) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                volunteerAvailability.getStartTime(), volunteerAvailability.getEndTime(), volunteerAvailability.isMonday(),
                volunteerAvailability.isTuesday(), volunteerAvailability.isWednesday(), volunteerAvailability.isThursday(),
                volunteerAvailability.isFriday(), volunteerAvailability.isSaturday(), volunteerAvailability.isSunday(),
                volunteerAvailability.getHobby(), volunteerAvailability.getEndDate(), idVolunteer, volunteerAvailability.getDniEderly());
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

    // Se actualiza endDate con la fecha actual (se cancela)
    public void cancelVolunteerAvailability(int id){
        LocalDate endDate =  LocalDate.now();
        jdbcTemplate.update("UPDATE VolunteerAvailability SET endDate = ? WHERE id = ?",
                endDate, id);
    }

    // Muestra la disponibilidad de un voluntario. Devuelve nulo si no existe.
    public VolunteerAvailability getVolunteerAvailability(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM VolunteerAvailability WHERE id = ?",
                    new VolunteerAvailabilityRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    // Muestra la disponibilidad de un voluntario con solicitantes. Devuelve nulo si no existe.
//    public VolunteerAvailability getVolunteerAvailabilityWithElderly(int volunteer, String dniElderly) {
//        try {
//            return jdbcTemplate.queryForObject("SELECT * FROM VolunteerAvailability WHERE idVolunteer = ? " +
//                    "AND dniElderly = ?", new VolunteerAvailabilityRowMapper(), volunteer, dniElderly);
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }

    // Muestra la disponibilidad de un voluntario con solicitantes. Devuelve nulo si no existe.
    public List<VolunteerAvailability> getVolunteerAvailabilities(int volunteer) {
        try {
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE idVolunteer = ? " +
                            "AND dniElderly is not null AND endDate is null",
                    new VolunteerAvailabilityRowMapper(), volunteer);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<VolunteerAvailability>();
        }
    }

    // Muestra la disponibilidad de un voluntario sin solicitantes. Devuelve nulo si no existe.
    public List<VolunteerAvailability> getVolunteerAvailabilitiesOutElderly(int volunteer) {
        try {
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE idVolunteer = ? " +
                            "AND dniElderly is null AND endDate is null",
                    new VolunteerAvailabilityRowMapper(), volunteer);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<VolunteerAvailability>();
        }
    }

    // Muestra la disponibilidad de todos los voluntarios un solicitante especifico. Devuelve nulo si no existe.
    public List<VolunteerAvailability> getVolunteersAvailabilityFromElderly(String dniElderly) {
        try {
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE dniElderly = ? AND endDate is null",
                    new VolunteerAvailabilityRowMapper(), dniElderly);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<VolunteerAvailability>();
        }
    }

    // Muestra todas las disponibilidades de los voluntarios con solicitantes.
    // Devuelve una lista vacia si no hay ninguna.
    public List<VolunteerAvailability> getAllVolunteerAvailabilities() {
        try {
            return jdbcTemplate.query("SELECT * FROM VolunteerAvailability WHERE dniElderly is null AND endDate is null",
                    new VolunteerAvailabilityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<VolunteerAvailability>();
        }
    }
}
