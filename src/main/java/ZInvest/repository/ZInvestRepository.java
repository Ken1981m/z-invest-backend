package ZInvest.repository;

import ZInvest.domain.*;
import ZInvest.domain.dto.*;
import ZInvest.mapping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ZInvestRepository {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public ZInvestRepository() {
    }

    public boolean leggTilLeilighet(Leilighet leilighet) {
        String sql = "INSERT INTO LEILIGHET (NAVN, ADRESSE, POSTNR, POSTSTED, BESKRIVELSE) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                leilighet.getNavn(),
                leilighet.getAdresse(),
                leilighet.getPostNr(),
                leilighet.getPostSted(),
                leilighet.getBeskrivelse());
        return true;
    }

    public boolean oppdaterLeilighet(LeilighetFormData leilighetFormData) {
        String sql = "UPDATE LEILIGHET SET navn = ?, adresse = ?, postnr = ?, poststed = ?, beskrivelse = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                leilighetFormData.getNavn(),
                leilighetFormData.getAdresse(),
                leilighetFormData.getPostnr(),
                leilighetFormData.getPoststed(),
                leilighetFormData.getBeskrivelse(),
                leilighetFormData.getId());
        return true;
    }

    public boolean slettLeilighet(Integer id) {
        String sql = "DELETE FROM LEILIGHET WHERE id = ?";
        jdbcTemplate.update(sql, id);
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

    public List<Inntekt> hentInntekt(Integer[] leilighetIds, int aar) {
        String placeholders = IntStream.range(0, leilighetIds.length)
                .mapToObj(i -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT SUM(BELOP) AS BELOP, AAR, MND " +
                     "FROM INNTEKT WHERE " +
                     "LEILIGHET_ID IN (" + placeholders + ") AND AAR = ? GROUP BY MND, AAR ORDER BY MND";

        Object[] params = new Object[leilighetIds.length + 1];
        System.arraycopy(leilighetIds, 0, params, 0, leilighetIds.length);
        params[leilighetIds.length] = aar;

        List<Inntekt> inntektList = jdbcTemplate.query(sql, params, new InntektRegnskapMapper());

        return inntektList;
    }


    public List<Inntekt> hentInntekt(int leilighetId, int inntektTypeId, int aar) {
        String sql = "SELECT ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND, BESKRIVELSE " +
                "FROM INNTEKT WHERE " +
                "LEILIGHET_ID = ? AND INNTEKT_TYPE_ID = ? AND AAR = ? ORDER BY MND";
        List<Inntekt> inntektList = jdbcTemplate.query(sql, new Object[]{leilighetId, inntektTypeId, aar}, new InntektMapper());
        return inntektList;
    }

    public List<Utgift> hentUtgift(Integer[] leilighetIds, int aar) {
        String placeholders = IntStream.range(0, leilighetIds.length)
                .mapToObj(i -> "?")
                .collect(Collectors.joining(","));

        String sql = "SELECT U.ID, U.LEILIGHET_ID, U.UTGIFT_TYPE_ID, U.BELOP, U.AAR, U.MND, U.BESKRIVELSE AS UTGIFT_BESKRIVELSE," +
                " UT.MAANEDUAVHENGIG, UT.NAVN AS UTGIFT_TYPE_NAVN, UT.BESKRIVELSE AS UTGIFT_TYPE_BESKRIVELSE " +
                "FROM UTGIFT U, UTGIFT_TYPE UT WHERE " +
                "U.UTGIFT_TYPE_ID = UT.ID AND " +
                "LEILIGHET_ID IN (" + placeholders + ") AND AAR = ? ORDER BY MND";

        Object[] params = new Object[leilighetIds.length + 1];
        System.arraycopy(leilighetIds, 0, params, 0, leilighetIds.length);
        params[leilighetIds.length] = aar;

        List<Utgift> utgiftList = jdbcTemplate.query(sql, params, new UtgiftMapper());
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
        String sql = "SELECT ID, NAVN, ADRESSE, POSTNR, POSTSTED, BESKRIVELSE FROM LEILIGHET ORDER BY NAVN";
        return jdbcTemplate.query(sql, new LeilighetMapper());
    }

    public Long hentSkatteprosent(int aar) {
        String sql = "SELECT SKATTEPROSENT FROM SKATTEPROSENT WHERE AAR = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{aar}, Long.class);
    }

    public List<Skatteprosent> hentSkatteprosent() {
        String sql = "SELECT ID, AAR, SKATTEPROSENT FROM SKATTEPROSENT ORDER BY AAR";
        return jdbcTemplate.query(sql, new SkatteprosentMapper());
    }

    public boolean leggTilSkatteprosent(SkatteprosentFormData skatteprosentFormData) {
        String sql = "INSERT INTO SKATTEPROSENT (AAR, SKATTEPROSENT) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(sql,
               skatteprosentFormData.getAar(),
               skatteprosentFormData.getSkatteprosent()
        );

        return true;
    }

    public boolean oppdaterSkatteprosent(SkatteprosentFormData skatteprosentFormData) {
        String sql = "UPDATE SKATTEPROSENT SET SKATTEPROSENT = ? WHERE ID = ?";
        jdbcTemplate.update(sql,
                skatteprosentFormData.getSkatteprosent(),
                skatteprosentFormData.getId());
        return true;
    }

    public boolean slettSkatteprosent(Integer id) {
        String sql = "DELETE FROM SKATTEPROSENT WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public List<FaktiskBetaltSkatt> hentFaktiskBetaltSkatt(Integer grupperingId) {
        String sql = "SELECT ID, GRUPPERING_ID, AAR, FAKTISK_SKATT_BELOP_FOR_UTLEIE, FAKTISK_SKATT_BELOP_ETTER_UTLEIE " +
                "FROM FAKTISK_BETALT_SKATT WHERE GRUPPERING_ID = ? ORDER BY AAR";
        return jdbcTemplate.query(sql, new Object[]{grupperingId}, new FaktiskBetaltSkattMapper());
    }

    public FaktiskBetaltSkatt hentFaktiskBetaltSkatt(Integer grupperingId, Integer aar) {
        String sql = "SELECT ID, GRUPPERING_ID, AAR, FAKTISK_SKATT_BELOP_FOR_UTLEIE, FAKTISK_SKATT_BELOP_ETTER_UTLEIE " +
                "FROM FAKTISK_BETALT_SKATT WHERE GRUPPERING_ID = ? AND AAR = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{grupperingId, aar}, new FaktiskBetaltSkattMapper());
    }

    public List<GrupperingBase> hentGrupperingBaser() {
        String sql = "SELECT ID, GRUPPERING_NAVN FROM GRUPPERING_BASE ORDER BY GRUPPERING_NAVN";
        return jdbcTemplate.query(sql, new GrupperingBaseMapper());
    }

    public List<GrupperingLeilighet> hentGrupperteLeilighet(Integer grupperingId) {
        String sql = "SELECT GB.ID AS GRUPPERING_ID, GB.GRUPPERING_NAVN, L.NAVN AS LEILIGHET_NAVN, L.ID as LEILIGHET_ID " +
                "FROM GRUPPERING_BASE GB, GRUPPERTE_LEILIGHETER GL, LEILIGHET L " +
                "WHERE GB.ID = GL.GRUPPERING_ID AND GL.LEILIGHET_ID = L.ID " +
                "AND GB.ID = ? ORDER BY L.NAVN";
        return jdbcTemplate.query(sql, new Object[]{grupperingId}, new GrupperingLeilighetMapper());
    }

    public boolean leggTilFaktiskBetaltSkatt(FaktiskSkattFormData faktiskSkattFormData) {
        String sql = "INSERT INTO FAKTISK_BETALT_SKATT (GRUPPERING_ID, AAR, FAKTISK_SKATT_BELOP_FOR_UTLEIE, FAKTISK_SKATT_BELOP_ETTER_UTLEIE) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                faktiskSkattFormData.getGrupperingId(),
                faktiskSkattFormData.getAar(),
                faktiskSkattFormData.getFaktiskSkattBelopForUtleieUtfyltISkattemelding(),
                faktiskSkattFormData.getFaktiskSkattBelopEtterUtleieUtfyltISkattemelding()
        );
        return true;
    }

    public boolean oppdaterFaktiskBetaltSkatt(FaktiskSkattFormData faktiskSkattFormData) {
        String sql = "UPDATE FAKTISK_BETALT_SKATT SET FAKTISK_SKATT_BELOP_FOR_UTLEIE = ?, " +
                "FAKTISK_SKATT_BELOP_ETTER_UTLEIE = ? WHERE AAR = ? AND GRUPPERING_ID = ?";
        jdbcTemplate.update(sql,
                faktiskSkattFormData.getFaktiskSkattBelopForUtleieUtfyltISkattemelding(),
                faktiskSkattFormData.getFaktiskSkattBelopEtterUtleieUtfyltISkattemelding(),
                faktiskSkattFormData.getAar(),
                faktiskSkattFormData.getGrupperingId());
        return true;
    }

    public boolean slettFaktiskBetaltSkatt(Integer id) {
        String sql = "DELETE FROM FAKTISK_BETALT_SKATT WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }
}
