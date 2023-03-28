package ZInvest.repository;

import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
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
