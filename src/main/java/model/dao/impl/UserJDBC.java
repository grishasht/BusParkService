package model.dao.impl;

import model.dao.Dao;
import model.dao.mappers.Mapper;
import model.dao.mappers.UserMapper;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserJDBC extends JDBC implements Dao<User> {
    public UserJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void create(User entity) {
        String query = "INSERT INTO users(login, password, name, email, role) " +
                " VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(connection.toString());
            log.info(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC creating");

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getRole().toString());
            preparedStatement.execute();

            log.info(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in UserJDBC creating");
            log.info(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC creating");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(properties.getProperty("SQL_EXC_WHILE_CREATE") + "in UserJDBC creating");
        }
    }

    @Override
    public List<User> readAll() {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM users";
        Mapper userMapper = new UserMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC readAll");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in UserJDBC readAll");

                while (resultSet.next()) {
                    users.add((User) userMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in UserJDBC");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC readAll");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in UserJDBC");
        }

        return users;
    }

    @Override
    public User readById(int id) {
        return null;
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(User entity) {
        String query = "DELETE FROM users WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC deleting");

            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in UserJDBC deleting");
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in RequestJDBC deleting");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_DELETE") + "in UserJDBC");
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
            log.debug(properties.getProperty("CONN_CLOSE") + "in UserJDBC");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_CLOSE_CONN") + "in UserJDBC");
        }
    }
}
