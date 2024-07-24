package ZInvest.mapping;

import ZInvest.domain.Inntekt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InntektMapper implements RowMapper<Inntekt> {

    @Override
    public Inntekt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inntekt inntekt = new Inntekt.Builder()
                .id(rs.getInt("ID"))
                .leilighetId(rs.getInt("LEILIGHET_ID"))
                .inntektTypeId(rs.getInt("INNTEKT_TYPE_ID"))
                .belop(rs.getDouble("BELOP"))
                .aar(rs.getInt("AAR"))
                .mnd(rs.getInt("MND"))
                .build();
        return inntekt;
    }
}
