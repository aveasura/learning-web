package org.learning.session.dao;

import org.learning.session.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Класс напрямую общается с бд и предоставляет данные репозиторию (CRUD)
public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public long create(String username, String password) {
        String sql = "INSERT INTO users (user_name, user_password) VALUES (?, ?) RETURNING user_id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return rs.getLong("user_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // При неудаче вернуть номер ошибки -1
        return -1;
    }

    @Override
    public List<User> readAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("user_id");
                String username = rs.getString("user_name");
                String password = rs.getString("user_password");

                // Создаем из полученных от ДБ данных пользователя и добавляем его в список.
                User user = new User(id, username, password);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User readByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            return new User(
                    rs.getLong("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User readById(long id) {

        return null;
    }

    @Override
    public void update(long id) {


    }

    @Override
    public void delete(long id) {


    }
}
