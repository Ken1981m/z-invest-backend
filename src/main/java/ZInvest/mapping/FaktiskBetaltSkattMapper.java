package ZInvest.mapping;

import ZInvest.domain.FaktiskBetaltSkatt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FaktiskBetaltSkattMapper implements RowMapper<FaktiskBetaltSkatt> {

    @Override
    public FaktiskBetaltSkatt mapRow(ResultSet rs, int rowNum) throws SQLException {
        FaktiskBetaltSkatt faktiskBetaltSkatt = new FaktiskBetaltSkatt.Builder()
                .id(rs.getInt("ID"))
                .grupperingId(rs.getInt("GRUPPERING_ID"))
                .aar(rs.getInt("AAR"))
                .faktiskSkattBelopForUtleieUtfyltISkattemelding(rs.getLong("FAKTISK_SKATT_BELOP_FOR_UTLEIE"))
                .faktiskSkattBelopEtterUtleieUtfyltISkattemelding(rs.getLong("FAKTISK_SKATT_BELOP_ETTER_UTLEIE"))
                .build();
        return faktiskBetaltSkatt;
    }
}
