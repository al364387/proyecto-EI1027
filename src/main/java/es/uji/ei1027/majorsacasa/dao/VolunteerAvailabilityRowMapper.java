package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.VolunteerAvailability;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class VolunteerAvailabilityRowMapper implements RowMapper<VolunteerAvailability> {

    @Override
    public VolunteerAvailability mapRow(ResultSet rs, int i) throws SQLException {
        VolunteerAvailability volunteerAvailability = new VolunteerAvailability();

        volunteerAvailability.setId(rs.getInt("id"));
        volunteerAvailability.setStartTime(rs.getObject("startTime", LocalTime.class));
        volunteerAvailability.setEndTime(rs.getObject("endTime", LocalTime.class));
        volunteerAvailability.setMonday(rs.getBoolean("monday"));
        volunteerAvailability.setTuesday(rs.getBoolean("tuesday"));
        volunteerAvailability.setWednesday(rs.getBoolean("wednesday"));
        volunteerAvailability.setThursday(rs.getBoolean("thursday"));
        volunteerAvailability.setFriday(rs.getBoolean("friday"));
        volunteerAvailability.setSaturday(rs.getBoolean("saturday"));
        volunteerAvailability.setSunday(rs.getBoolean("sunday"));
        volunteerAvailability.setHobby(rs.getString("hobby"));
        volunteerAvailability.setEndDate(rs.getObject("endDate", LocalDate.class));
        volunteerAvailability.setIdVolunteer(rs.getInt("idVolunteer"));
        volunteerAvailability.setDniEderly(rs.getString("dniElderly"));

        return volunteerAvailability;
    }
}
