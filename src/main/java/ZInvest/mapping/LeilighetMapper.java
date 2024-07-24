package ZInvest.mapping;

import ZInvest.domain.Leilighet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeilighetMapper implements RowMapper<Leilighet> {

    @Override
    public Leilighet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Leilighet leilighet = new Leilighet.Builder()
                .id(rs.getInt("ID"))
                .navn(rs.getString("NAVN"))
                .adresse(rs.getString("ADRESSE"))
                .postNr(rs.getString("POSTNR"))
                .postSted(rs.getString("POSTSTED"))
                .build();
        return leilighet;
    }
}
