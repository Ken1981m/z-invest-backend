package ZInvest.mapping;

import ZInvest.domain.GrupperingBase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupperingBaseMapper implements RowMapper<GrupperingBase> {

    @Override
    public GrupperingBase mapRow(ResultSet rs, int rowNum) throws SQLException {
        GrupperingBase grupperingBase = new GrupperingBase.Builder()
                .id(rs.getInt("ID"))
                .grupperingNavn(rs.getString("GRUPPERING_NAVN"))
                .build();
        return grupperingBase;
    }
}
