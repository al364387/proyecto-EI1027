package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.dao.ContractDao;
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
    public void addRequest(Request peticion) {
        ContractDao contractDao = new ContractDao();
        Contract contrato = contractDao.getContract(peticion.getContractId());

        boolean catering = contrato.isCatering();
        boolean nursing = contrato.isNursing();
        boolean cleaning = contrato.isCleaning();

        jdbcTemplate.update("INSERT INTO Request(state, startDate, endDate, time, catering, nursing, cleaning," +
                        " description, elderlyId, contractId) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                peticion.getState(), peticion.getStartDate(),
                peticion.getEndDate(), peticion.getTime(), catering,
                nursing, cleaning, peticion.getDescription(),
                peticion.getElderlyId(), peticion.getContractId());
    }

    /* Esborra la request de la base de dades LOS REQUEST NO DEBERÍAN BORRARSE*/
    /*
    public void deleteRequest(Request peticion) {
        jdbcTemplate.update("DELETE FROM Request WHERE number = ?", peticion.getNumber());
    }
    */

    /* Actualitza els atributs de la request */
    public void updateRequest(Request peticion) {
        jdbcTemplate.update("UPDATE Request SET state = ?, description = ?", peticion.getState(), peticion.getDescription());
    }

    /* Obté el request amb el nom donat. Torna null si no existeix. */
    public Request getRequest(int number) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Request WHERE number = ?", new RequestRowMapper(), number);
        } catch (EmptyResultDataAccessException e) {
            return null;
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
