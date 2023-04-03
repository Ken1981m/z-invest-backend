package ZInvest.repository;

import ZInvest.domain.Inntekt;
import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.UtgiftType;
import ZInvest.service.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZInvestRepository {
    @Autowired
    DatabaseUtil databaseUtil;

    public ZInvestRepository() {
    }

    public void leggTilLeilighet(Leilighet leilighet) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO LEILIGHET (ID, NAVN, ADRESSE, POSTNR, POSTSTED) VALUES (LEILIGHET_SEQ.NEXTVAL, ?, ?, ?, ?)";
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, leilighet.getNavn());
            statement.setString(2, leilighet.getAdresse());
            statement.setString(3, leilighet.getPostNr());
            statement.setString(4, leilighet.getPostSted());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public void leggTilInntekt(int leilighetId, int inntektTypeId, String mnd, String aar, Double belop) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO INNTEKT (ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND) " +
                    "VALUES (INNTEKT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, leilighetId);
            statement.setInt(2, inntektTypeId);
            statement.setDouble(3, belop);
            statement.setString(4, aar);
            statement.setString(5, mnd);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public void leggTilUtgift(int leilighetId, int utgiftTypeId, String mnd, String aar, Double belop) {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO UTGIFT (ID, LEILIGHET_ID, UTGIFT_TYPE_ID, BELOP, AAR, MND) " +
                    "VALUES (UTGIFT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, leilighetId);
            statement.setInt(2, utgiftTypeId);
            statement.setDouble(3, belop);
            statement.setString(4, aar);
            statement.setString(5, mnd);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public InntektType hentInntektType(int id) {
        PreparedStatement statement = null;
        ResultSet rs;
        try {
            String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE WHERE ID = ?";
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
               return new InntektType.Builder()
                                .id(rs.getInt("ID"))
                                .navn(rs.getString("NAVN"))
                                .beskrivelse(rs.getString("BESKRIVELSE"))
                                .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
        return null;
    }

    public List<InntektType> hentInntektTyper() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<InntektType> inntektTypeList = new ArrayList<>();
        try {
            String sql = "SELECT ID, NAVN, BESKRIVELSE FROM INNTEKT_TYPE";
            statement = getConnection().prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                inntektTypeList.add(
                        new InntektType.Builder()
                                .id(rs.getInt("ID"))
                                .navn(rs.getString("NAVN"))
                                .beskrivelse(rs.getString("BESKRIVELSE"))
                                .build());
            }

            return inntektTypeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public List<Inntekt> hentInntekt(int leilighetId, int aar) {
        PreparedStatement statement = null;
        ResultSet rs;
        List<Inntekt> inntektList = new ArrayList<>();
        try {
            String sql = "SELECT ID, LEILIGHET_ID, INNTEKT_TYPE_ID, BELOP, AAR, MND " +
                         "FROM INNTEKT WHERE " +
                         "LEILIGHET_ID = ? AND AAR = ?";
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, leilighetId);
            statement.setInt(2, aar);
            rs = statement.executeQuery();

            while (rs.next()) {
                inntektList.add(
                        new Inntekt.Builder()
                                .id(rs.getInt("ID"))
                                .leilighet(hentLeilighet(rs.getInt("LEILIGHET_ID")))
                                .inntektType(hentInntektType(rs.getInt("INNTEKT_TYPE_ID")))
                                .belop(rs.getDouble("BELOP"))
                                .aar(rs.getInt("AAR"))
                                .mnd(rs.getInt("MND"))
                                .build());
            }

            return inntektList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public List<UtgiftType> hentUtgiftTyper() {
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<UtgiftType> utgiftTypeList = new ArrayList<>();
        try {
            String sql = "SELECT ID, NAVN, BESKRIVELSE FROM UTGIFT_TYPE";
            statement = getConnection().prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                utgiftTypeList.add(
                        new UtgiftType.Builder()
                                .id(rs.getInt("ID"))
                                .navn(rs.getString("NAVN"))
                                .beskrivelse(rs.getString("BESKRIVELSE"))
                                .build());
            }

            return utgiftTypeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }

    public Leilighet hentLeilighet(int id) {
        PreparedStatement statement = null;
        ResultSet rs;
        try {
            String sql = "SELECT ID, NAVN, ADRESSE, POSTNR, POSTSTED FROM LEILIGHET WHERE ID = ?";
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                return new Leilighet.Builder()
                                .id(rs.getInt("ID"))
                                .navn(rs.getString("NAVN"))
                                .adresse(rs.getString("ADRESSE"))
                                .postNr(rs.getString("POSTNR"))
                                .postSted(rs.getString("POSTSTED"))
                                .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
        return null;
    }

    public List<Leilighet> hentLeiligheter() {
        PreparedStatement statement = null;
        ResultSet rs;
        List<Leilighet> leilighetList = new ArrayList<>();
        try {
            String sql = "SELECT ID, NAVN, ADRESSE, POSTNR, POSTSTED FROM LEILIGHET";
            statement = getConnection().prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                leilighetList.add(
                        new Leilighet.Builder()
                                .id(rs.getInt("ID"))
                                .navn(rs.getString("NAVN"))
                                .adresse(rs.getString("ADRESSE"))
                                .postNr(rs.getString("POSTNR"))
                                .postSted(rs.getString("POSTSTED"))
                                .build());
            }

            return leilighetList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(getConnection(), statement, null);
        }
    }


    private void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        return databaseUtil.getConnection();
    }
}
