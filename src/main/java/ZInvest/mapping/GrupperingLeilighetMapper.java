package ZInvest.mapping;

import ZInvest.domain.GrupperingLeilighet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrupperingLeilighetMapper implements RowMapper<GrupperingLeilighet> {

    @Override
    public GrupperingLeilighet mapRow(ResultSet rs, int rowNum) throws SQLException {
        GrupperingLeilighet grupperingLeilighet = new GrupperingLeilighet.Builder()
                .id(rs.getInt("GRUPPERING_ID"))
                .grupperingNavn(rs.getString("GRUPPERING_NAVN"))
                .leilighetNavn(rs.getString("LEILIGHET_NAVN"))
                .build();
        return grupperingLeilighet;
    }
}