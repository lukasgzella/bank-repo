package org.kaczucha.repository;

import org.kaczucha.repository.annotation.InMemoryRepository;
import org.kaczucha.repository.annotation.JdbcRepository;
import org.kaczucha.repository.entity.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
@JdbcRepository
public class JDBCClientRepository implements ClientRepository {
    public final String user;
    public final String password;
    public final String jdbcUrl;

    public JDBCClientRepository(
            @Value("${jdbc.user}") String user,
            @Value("${jdbc.password}") String password,
            @Value("${jdbc.url}") String jdbcUrl
    ) {
        this.user = user;
        this.password = password;
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public void save(Client client) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            final String name = client.getName();
            final String email = client.getEmail();
            final PreparedStatement statement
                    = connection.prepareStatement("INSERT INTO USERS(FIRST_NAME,MAIL) VALUES (?,?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findByEmail(String email) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            final PreparedStatement statement = connection.prepareStatement("SELECT FIRST_NAME,MAIL FROM USERS WHERE MAIL = ?");
            statement.setString(1, email);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                final String name = resultSet.getString("FIRST_NAME");
                final String mail = resultSet.getString("MAIL");
                return new Client(name, mail, null);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
