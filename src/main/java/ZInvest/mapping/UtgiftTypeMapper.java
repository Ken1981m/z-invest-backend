package ZInvest.mapping;

import ZInvest.domain.UtgiftType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtgiftTypeMapper implements RowMapper<UtgiftType> {

    @Override
    public UtgiftType mapRow(ResultSet rs, int rowNum) throws SQLException {
        UtgiftType utgiftType = new UtgiftType.Builder()
                .id(rs.getInt("ID"))
                .navn(rs.getString("NAVN"))
                .beskrivelse(rs.getString("BESKRIVELSE"))
                .mndUavhengig(rs.getInt("MAANEDUAVHENGIG") == 1 ? true : false)
                .build();
        return utgiftType;
    }
}
