package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository // En Spring els DAOs van anotats amb @Repository
public class RequestDao {
    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix la request a la base de dades */
    public void addRequest(Request peticion, String dni, ContractDao contractDao) {
//        Time time;
//
//        if(peticion.getTime() == null){
//            time = new Time(10);
//        } else {
//            time = peticion.getTime();
//        }

        boolean catering = contractDao.getContract(peticion.getContractId()).isCatering();
        boolean nursing = contractDao.getContract(peticion.getContractId()).isNursing();
        boolean cleaning = contractDao.getContract(peticion.getContractId()).isCleaning();

        jdbcTemplate.update("INSERT INTO Request(state, startDate, endDate, time, catering, nursing, cleaning," +
                        " description, elderlyId, contractId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                peticion.getState(), peticion.getStartDate(),
                peticion.getEndDate(), peticion.getTime(), catering,
                nursing, cleaning, peticion.getDescription(),
                dni, peticion.getContractId());
    }

    /* Actualitza els atributs de la request */
    public void updateRequest(Request peticion) {
        jdbcTemplate.update("UPDATE Request SET state = ?, description = ?",
                peticion.getState(), peticion.getDescription());
    }

    public void updateRequestStatus(int number, String estado) {
        jdbcTemplate.update("UPDATE Request SET state = ? WHERE number = ?", estado, number);
    }

    /* Obté el request amb el nom donat. Torna null si no existeix. */
    public Request getRequest(int number) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Request WHERE number = ?",
                    new RequestRowMapper(), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /* Obté tots els request d'una persona major. Torna una llista buida si no n'hi ha cap. */
    public List<Request> getRequestsFromEldely(String dniElderly) {
        try {
            return jdbcTemplate.query("SELECT * FROM Request WHERE elderlyId = ?",
                    new RequestRowMapper(), dniElderly);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Request>();
        }
    }

    /* Obté tots els request. Torna una llista buida si no n'hi ha cap. */
    public List<Request> getRequests() {
        try {
            return jdbcTemplate.query("SELECT * FROM Request", new RequestRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Request>();
        }
    }
}
