package ZInvest.mapping;

import ZInvest.domain.Skatteprosent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkatteprosentMapper implements RowMapper<Skatteprosent> {

    @Override
    public Skatteprosent mapRow(ResultSet rs, int rowNum) throws SQLException {
        Skatteprosent skatteprosent = new Skatteprosent.Builder()
                .id(rs.getInt("ID"))
                .aar(rs.getInt("AAR"))
                .skatteprosent(rs.getInt("SKATTEPROSENT"))
                .build();
        return skatteprosent;
    }
}