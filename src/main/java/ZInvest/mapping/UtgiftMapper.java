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
                .utgiftTypeId(rs.getInt("UTGIFT_TYPE_ID"))
                .utgiftTypeNavn(rs.getString("UTGIFT_TYPE_NAVN"))
                .utgiftTypeBeskrivelse(rs.getString("UTGIFT_TYPE_BESKRIVELSE"))
                .belop(rs.getDouble("BELOP"))
                .aar(rs.getInt("AAR"))
                .mnd(rs.getInt("MND"))
                .beskrivelse(rs.getString("UTGIFT_BESKRIVELSE"))
                .mndUavhengig(rs.getInt("MAANEDUAVHENGIG") == 1 ? true : false)
                .build();
        return utgift;
    }
}
