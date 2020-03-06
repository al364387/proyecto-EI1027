package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Añade el contrato a la bbdd
    public void addContract(Contract contract){
        jdbcTemplate.update("INSERT INTO Contract VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                contract.getNumber(), contract.isCatering(), contract.isNursing(),
                contract.isCleaning(), contract.getPrice(), contract.getStartDate(),
                contract.getEndDate(), contract.getServiceNumber(), contract.getCifCompany());
    }

    // Obtiene los datos de un contrato. Devuelve null si no existe.
    public Contract getContract(String numContract){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Contract WHERE numContract = ?",
                    new ContractRowMapper(), numContract);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    // Obtiene todos los contratos. Devuelve una lista vacia si no hay ninguno.
    public List<Contract> getContracts(){
        try{
            return jdbcTemplate.query("SELECT * FROM Contract", new ContractRowMapper());
        } catch (EmptyResultDataAccessException e){
            return new ArrayList<Contract>();
        }
    }
}
