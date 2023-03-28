package ZInvest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseUtil {
    private Connection connection = null;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driver;

    public DatabaseUtil() {
    }

    public Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName(driver);

            // Establish a connection to the database
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
        }
        return connection;
    }
}
