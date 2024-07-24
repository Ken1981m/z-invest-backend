package ZInvest.mapping;

import ZInvest.domain.InntektType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InntektTypeMapper implements RowMapper<InntektType> {

    @Override
    public InntektType mapRow(ResultSet rs, int rowNum) throws SQLException {
        InntektType inntektType = new InntektType.Builder()
                .id(rs.getInt("ID"))
                .navn(rs.getString("NAVN"))
                .beskrivelse(rs.getString("BESKRIVELSE"))
                .build();
        return inntektType;
    }
}
