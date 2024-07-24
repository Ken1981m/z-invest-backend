package ZInvest.repository;

import ZInvest.domain.Inntekt;
import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.UtgiftType;
import ZInvest.mapping.InntektMapper;
import ZInvest.mapping.InntektTypeMapper;
import ZInvest.mapping.LeilighetMapper;
import ZInvest.mapping.UtgiftTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ZInvestRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public ZInvestRepository() {
    }

    public boolean leggTilLeilighet(Leilighet leilighet) {
        String sql = "INSERT INTO LEILIGHET (NAVN, ADRESSE, POSTNR, POSTSTED) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                leilighet.getNavn(),
                leilighet.getAdresse(),
                leilighet.getPostNr(),
                leilighet.getPostSted());
        return true;
    }

    public boolean leggTilInntekt(int leilighetId, int inntektTypeId, int mnd, int aar, Double belop) {
        String sql = "INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, leilighetId, inntektTypeId, belop, aar, mnd);
        return true;
    }

    public boolean leggTilUtgift(int leilighetId, int utgiftTypeId, int mnd, int aar, Double belop) {
        String sql = "INSERT INTO UTGIFT (LEILIGHET_ID, UTGIFT_TYPE_ID, BELOP, AAR, MND) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, leilighetId, utgiftTypeId, belop, aar, mnd);

        return true;
    }

    public InntektType hentInntektType(int id) {
       String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE WHERE ID = ?";
       return jdbcTemplate.queryForObject(sql, new Object[]{id}, new InntektTypeMapper());
    }

    public List<InntektType> hentInntektTyper() {
       String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE";
       return jdbcTemplate.query(sql, new InntektTypeMapper());
    }

    public List<Inntekt> hentInntekt(int leilighetId, int aar) {
        String sql = "SELECT ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND " +
                     "FROM INNTEKT WHERE " +
                     "LEILIGHET_ID = ? AND AAR = ? ORDER BY MND";
        List<Inntekt> inntektList = jdbcTemplate.query(sql, new Object[]{leilighetId, aar}, new InntektMapper());

        inntektList.stream().forEach(inntekt -> {
            inntekt.setLeilighet(hentLeilighet(inntekt.getLeilighetId()));
            inntekt.setInntektType(hentInntektType(inntekt.getInntektTypeId()));
        });
        return inntektList;
    }

    public List<UtgiftType> hentUtgiftTyper() {
        String sql = "SELECT ID, NAVN, BESKRIVELSE FROM UTGIFT_TYPE";
        return jdbcTemplate.query(sql, new UtgiftTypeMapper());
    }

    public Leilighet hentLeilighet(int id) {
        String sql = "SELECT ID, NAVN, ADRESSE, POSTNR, POSTSTED FROM LEILIGHET WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new LeilighetMapper());
    }

    public List<Leilighet> hentLeiligheter() {
        String sql = "SELECT ID, NAVN, ADRESSE, POSTNR, POSTSTED FROM LEILIGHET ORDER BY NAVN";
        return jdbcTemplate.query(sql, new LeilighetMapper());
    }
}
