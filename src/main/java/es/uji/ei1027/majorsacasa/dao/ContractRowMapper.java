package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ContractRowMapper implements RowMapper<Contract> {
    public Contract mapRow(ResultSet rs, int i) throws SQLException {
        Contract contract = new Contract();
        contract.setNumContract(rs.getString("numContract"));
        contract.setCatering(rs.getBoolean("catering"));
        contract.setNursing(rs.getBoolean("nursing"));
        contract.setCleaning(rs.getBoolean("cleaning"));
        contract.setPrice(rs.getFloat("price"));
        contract.setStartDate(rs.getObject("startDate", LocalDate.class));
        contract.setEndDate(rs.getObject("endDate", LocalDate.class));
        contract.setServiceNumber(rs.getInt("serviceNumber"));
        contract.setCifCompany(rs.getString("cifCompany"));
        return contract;
    }
}
