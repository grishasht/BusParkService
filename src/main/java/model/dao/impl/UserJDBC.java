package model.dao.impl;

import model.dao.Dao;
import model.dao.UserDao;
import model.dao.mappers.Mapper;
import model.dao.mappers.UserMapper;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserJDBC extends JDBC implements UserDao {
    public UserJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void create(User entity) {
        String query = "INSERT INTO users(login, password, name, email, role, age, is_free) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(connection.toString());
            log.info(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC creating");

            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getRole().toString());
            preparedStatement.setInt(6, entity.getAge());
            preparedStatement.setBoolean(7, true);
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
                    users.add((User) userMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6, 7, 8));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in UserJDBC readAll");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC readAll");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in UserJDBC");
        }

        return users;
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE users SET name = ?, age = ?, is_free = ?" +
                " WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC update");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setBoolean(3, entity.getFree());
            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in UserJDBC update");
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC update");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_UPDATE") + "in UserJDBC");
        }
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
    public List<User> getDrivers() {
        List<User> users = new LinkedList<>();
        String query = "SELECT * FROM users where role='driver'";
        Mapper userMapper = new UserMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC getDrivers");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in UserJDBC getDrivers");

                while (resultSet.next()) {
                    users.add((User) userMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6, 7, 8));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in UserJDBC getDrivers");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC getDrivers");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in UserJDBC");
        }

        return users;
    }

    @Override
    public User readById(int id) {
        User user = null;
        String query = "SELECT * FROM users where id=" + id;
        Mapper userMapper = new UserMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in UserJDBC readByID");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in UserJDBC readByID");

                while (resultSet.next()) {
                    user = ((User) userMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6, 7, 8));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in UserJDBC readByID");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in UserJDBC readByID");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in UserJDBC");
        }

        return user;
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.debug(properties.getProperty("CONN_CLOSE") + "in UserJDBC");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_CLOSE_CONN") + "in UserJDBC");
        }
    }
}
