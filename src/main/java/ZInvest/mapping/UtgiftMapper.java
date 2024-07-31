package ZInvest.mapping;

import ZInvest.domain.Utgift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtgiftMapper implements RowMapper<Utgift> {

    @Override
    public Utgift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Utgift utgift = new Utgift.Builder()
                .id(rs.getInt("ID"))
                .leilighetId(rs.getInt("LEILIGHET_ID"))
                .inntektTypeId(rs.getInt("UTGIFT_TYPE_ID"))
                .belop(rs.getDouble("BELOP"))
                .aar(rs.getInt("AAR"))
                .mnd(rs.getInt("MND"))
                .beskrivelse(rs.getString("BESKRIVELSE"))
                .build();
        return utgift;
    }
}
