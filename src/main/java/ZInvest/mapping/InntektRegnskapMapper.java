package ZInvest.mapping;

import ZInvest.domain.Inntekt;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InntektRegnskapMapper implements RowMapper<Inntekt> {

    @Override
    public Inntekt mapRow(ResultSet rs, int rowNum) throws SQLException {
        Inntekt inntekt = new Inntekt.Builder()
                .belop(rs.getLong("BELOP"))
                .aar(rs.getInt("AAR"))
                .mnd(rs.getInt("MND"))
                .build();
        return inntekt;
    }
}
