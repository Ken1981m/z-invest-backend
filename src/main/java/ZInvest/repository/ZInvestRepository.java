package ZInvest.repository;

import ZInvest.domain.*;
import ZInvest.domain.dto.InntektFormData;
import ZInvest.domain.dto.InntektTypeFormData;
import ZInvest.domain.dto.UtgiftFormData;
import ZInvest.domain.dto.UtgiftTypeFormData;
import ZInvest.mapping.*;
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

    public boolean leggtilInntektType(InntektTypeFormData inntektTypeFormData) {
        String sql = "INSERT INTO INNTEKT_TYPE (NAVN, BESKRIVELSE) VALUES (?, ?)";
        jdbcTemplate.update(sql, inntektTypeFormData.getNavn(), inntektTypeFormData.getBeskrivelse());
        return true;
    }

    public boolean oppdaterInntektType(InntektTypeFormData inntektTypeFormData) {
        String sql = "UPDATE INNTEKT_TYPE SET navn = ?, beskrivelse = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                inntektTypeFormData.getNavn(),
                inntektTypeFormData.getBeskrivelse(),
                inntektTypeFormData.getId());
        return true;
    }

    public boolean slettInntektType(Integer id) {
        String sql = "DELETE FROM INNTEKT_TYPE WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public boolean leggTilInntekt(InntektFormData inntektFormData) {
        String sql = "INSERT INTO INNTEKT (LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND, BESKRIVELSE) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                Integer.parseInt(inntektFormData.getLeilighetId()),
                Integer.parseInt(inntektFormData.getInntektTypeId()),
                inntektFormData.getBelop(),
                Integer.parseInt(inntektFormData.getFormatertDato()
                        .substring(inntektFormData.getFormatertDato().indexOf("-")+1)),
                inntektFormData.getMnd() != null
                        ? inntektFormData.getMnd()
                        : Integer.parseInt(inntektFormData.getFormatertDato()
                          .substring(0, inntektFormData.getFormatertDato().indexOf("-"))),
                inntektFormData.getBeskrivelse()
        );
        return true;
    }

    public boolean oppdaterInntekt(InntektFormData inntektFormData) {
        String sql = "UPDATE INNTEKT SET BELOP = ?, BESKRIVELSE = ? WHERE LEILIGHET_ID = ? AND INNTEKT_TYPE_ID = ? " +
                "AND AAR = ? AND MND = ?";
        jdbcTemplate.update(sql,
                inntektFormData.getBelop(),
                inntektFormData.getBeskrivelse(),
                Integer.parseInt(inntektFormData.getLeilighetId()),
                Integer.parseInt(inntektFormData.getInntektTypeId()),
                Integer.parseInt(inntektFormData.getFormatertDato()
                        .substring(inntektFormData.getFormatertDato().indexOf("-")+1)),
                inntektFormData.getMnd());
        return true;
    }

    public boolean slettInntekt(Integer id) {
        String sql = "DELETE FROM INNTEKT WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public boolean leggTilUtgiftType(UtgiftTypeFormData utgiftTypeFormData) {
        String sql = "INSERT INTO UTGIFT_TYPE (NAVN, BESKRIVELSE, MAANEDUAVHENGIG) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                utgiftTypeFormData.getNavn(),
                utgiftTypeFormData.getBeskrivelse(),
                utgiftTypeFormData.getMnduavhengig());
        return true;
    }

    public boolean oppdaterUtgiftType(UtgiftTypeFormData utgiftTypeFormData) {
        String sql = "UPDATE UTGIFT_TYPE SET navn = ?, beskrivelse = ?, maaneduavhengig = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                utgiftTypeFormData.getNavn(),
                utgiftTypeFormData.getBeskrivelse(),
                utgiftTypeFormData.getMnduavhengig(),
                utgiftTypeFormData.getId());
        return true;
    }

    public boolean slettUtgiftType(Integer id) {
        String sql = "DELETE FROM UTGIFT_TYPE WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public boolean leggTilUtgift(UtgiftFormData utgiftFormData) {
        String sql = "INSERT INTO UTGIFT (LEILIGHET_ID, UTGIFT_TYPE_ID, BELOP, AAR, MND, BESKRIVELSE) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                Integer.parseInt(utgiftFormData.getLeilighetId()),
                Integer.parseInt(utgiftFormData.getUtgiftTypeId()),
                utgiftFormData.getBelop(),
                Integer.parseInt(utgiftFormData.getFormatertDato()
                        .substring(utgiftFormData.getFormatertDato().indexOf("-") + 1)),
                utgiftFormData.getMnd() != null
                        ? utgiftFormData.getMnd()
                        : Integer.parseInt(utgiftFormData.getFormatertDato()
                        .substring(0, utgiftFormData.getFormatertDato().indexOf("-"))),
                utgiftFormData.getBeskrivelse()
        );

        return true;
    }

    public boolean leggTilUtgiftUtenMnd(UtgiftFormData utgiftFormData) {
        String sql = "INSERT INTO UTGIFT (LEILIGHET_ID, UTGIFT_TYPE_ID, BELOP, AAR, BESKRIVELSE) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                Integer.parseInt(utgiftFormData.getLeilighetId()),
                Integer.parseInt(utgiftFormData.getUtgiftTypeId()),
                utgiftFormData.getBelop(),
                Integer.parseInt(utgiftFormData.getFormatertDato()
                        .substring(utgiftFormData.getFormatertDato().indexOf("-") + 1)),
                utgiftFormData.getBeskrivelse()
        );

        return true;
    }


    public boolean oppdaterUtgift(UtgiftFormData utgiftFormData) {
        String sql = "UPDATE UTGIFT SET BELOP = ?, BESKRIVELSE = ? WHERE ID = ?";
        jdbcTemplate.update(sql,
                utgiftFormData.getBelop(),
                utgiftFormData.getBeskrivelse(),
                utgiftFormData.getId());
        return true;
    }

    public boolean slettUtgift(Integer id) {
        String sql = "DELETE FROM UTGIFT WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public InntektType hentInntektType(int id) {
       String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE WHERE ID = ?";
       return jdbcTemplate.queryForObject(sql, new Object[]{id}, new InntektTypeMapper());
    }

    public List<InntektType> hentInntektTyper() {
       String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE ORDER BY NAVN";
       return jdbcTemplate.query(sql, new InntektTypeMapper());
    }

    public List<Inntekt> hentInntekt(int leilighetId, int aar) {
        String sql = "SELECT ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND, BESKRIVELSE " +
                     "FROM INNTEKT WHERE " +
                     "LEILIGHET_ID = ? AND AAR = ? ORDER BY MND";
        List<Inntekt> inntektList = jdbcTemplate.query(sql, new Object[]{leilighetId, aar}, new InntektMapper());

        inntektList.stream().forEach(inntekt -> {
            inntekt.setLeilighet(hentLeilighet(inntekt.getLeilighetId()));
            inntekt.setInntektType(hentInntektType(inntekt.getInntektTypeId()));
        });
        return inntektList;
    }


    public List<Inntekt> hentInntekt(int leilighetId, int inntektTypeId, int aar) {
        String sql = "SELECT ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND, BESKRIVELSE " +
                "FROM INNTEKT WHERE " +
                "LEILIGHET_ID = ? AND INNTEKT_TYPE_ID = ? AND AAR = ? ORDER BY MND";
        List<Inntekt> inntektList = jdbcTemplate.query(sql, new Object[]{leilighetId, inntektTypeId, aar}, new InntektMapper());
        return inntektList;
    }

    public List<Utgift> hentUtgift(int leilighetId, int aar) {
        String sql = "SELECT U.ID, U.LEILIGHET_ID, U.UTGIFT_TYPE_ID, U.BELOP, U.AAR, U.MND, U.BESKRIVELSE AS UTGIFT_BESKRIVELSE," +
                " UT.MAANEDUAVHENGIG, UT.NAVN AS UTGIFT_TYPE_NAVN, UT.BESKRIVELSE AS UTGIFT_TYPE_BESKRIVELSE " +
                "FROM UTGIFT U, UTGIFT_TYPE UT WHERE " +
                "U.UTGIFT_TYPE_ID = UT.ID AND " +
                "LEILIGHET_ID = ? AND AAR = ? ORDER BY MND";
        List<Utgift> utgiftList = jdbcTemplate.query(sql, new Object[]{leilighetId, aar}, new UtgiftMapper());
        return utgiftList;
    }


    public List<Utgift> hentUtgift(int leilighetId, int utgiftTypeId, int aar) {
        String sql = "SELECT U.ID, U.LEILIGHET_ID, U.UTGIFT_TYPE_ID, U.BELOP, U.AAR, U.MND, U.BESKRIVELSE AS UTGIFT_BESKRIVELSE," +
                " UT.MAANEDUAVHENGIG, UT.NAVN AS UTGIFT_TYPE_NAVN, UT.BESKRIVELSE AS UTGIFT_TYPE_BESKRIVELSE " +
                "FROM UTGIFT U, UTGIFT_TYPE UT WHERE " +
                "U.UTGIFT_TYPE_ID = UT.ID AND " +
                "LEILIGHET_ID = ? AND UTGIFT_TYPE_ID = ? AND AAR = ? ORDER BY MND";
        List<Utgift> utgiftList = jdbcTemplate.query(sql, new Object[]{leilighetId, utgiftTypeId, aar}, new UtgiftMapper());
        return utgiftList;
    }

    public UtgiftType hentUtgiftType(int id) {
        String sql = "SELECT ID, NAVN, BESKRIVELSE, MAANEDUAVHENGIG FROM UTGIFT_TYPE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id}, new UtgiftTypeMapper());
    }

    public List<UtgiftType> hentUtgiftTyper() {
        String sql = "SELECT ID, NAVN, BESKRIVELSE, MAANEDUAVHENGIG FROM UTGIFT_TYPE ORDER BY NAVN";
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
