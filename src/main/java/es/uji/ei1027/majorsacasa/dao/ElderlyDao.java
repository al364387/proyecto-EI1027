package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Elderly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElderlyDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Añade a la persona mayor a la bbdd
    public void addElderly(Elderly elderly){
        jdbcTemplate.update("INSERT INTO Elderly VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                elderly.getSocialAssisId(), elderly.getName(), elderly.getSurname(),
                elderly.getEmail(), elderly.getDNI(), elderly.getBirthDate(),
                elderly.getPhoneNumber(), elderly.getAddress(), elderly.getBankAccount(),
                elderly.getUsername(), elderly.getPassword());
    }

    // Actualiza los datos de una persona mayor
    public void upadateElderly(Elderly elderly){
        jdbcTemplate.update("UPDATE Elderly SET socialAssisId = ?, name = ?, surname = ?, email = ?, birthDate = ?, " +
                "phoneNumber = ?, address = ?, bankAccount = ?, username = ?, password = ? WHERE dni = ?",
                elderly.getSocialAssisId(), elderly.getName(), elderly.getSurname(), elderly.getEmail(),
                elderly.getBirthDate(), elderly.getPhoneNumber(), elderly.getAddress(), elderly.getBankAccount(),
                elderly.getUsername(), elderly.getPassword(), elderly.getDNI());
    }

    // Borra a la persona mayor de la bbdd
    public void deleteElderly(String dni){
        jdbcTemplate.update("DELETE FROM Elderly WHERE dni = ?", dni);
    }

    // Muestra los datos de una persona mayor. Devuelve nulo si no existe
    public Elderly getElderly(String dni){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Elderly WHERE dni = ?", new ElderlyRowMapper(), dni);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Muestra todos los datos de las personas mayores
    public List<Elderly> getElderlies(){
        try {
            return jdbcTemplate.query("SELECT * FROM Elderly", new ElderlyRowMapper());
        } catch (EmptyResultDataAccessException e){
            return new ArrayList<Elderly>();
        }
    }
}
