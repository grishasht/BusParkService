package model.dao.impl;

import model.dao.BusDao;
import model.dao.Dao;
import model.dao.mappers.BusMapper;
import model.dao.mappers.Mapper;
import model.entity.Bus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class BusJDBC extends JDBC implements BusDao {
    BusJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Bus entity) {
        String query = "INSERT INTO buses(name, number, places_num, is_free) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.info(properties.getProperty("PREP_STAT_OPEN") + "in BusJDBC creating");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setInt(3, entity.getNumPlaces());
            preparedStatement.setBoolean(4, entity.getFree());

            preparedStatement.execute();

            log.info(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in BusJDBC creating");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(properties.getProperty("SQL_EXC_WHILE_CREATE") + "in BusJDBC creating");
        }
    }

    @Override
    public List<Bus> readAll() {
        List<Bus> buses = new LinkedList<>();
        String query = "SELECT * FROM buses";
        Mapper busMapper = new BusMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in BusJDBC readAll");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in BusJDBC readAll");

                while (resultSet.next()) {
                    buses.add((Bus) busMapper.getEntity(resultSet, 1, 2, 3, 4, 5));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in BusJDBC");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in BusJDBC readAll");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in BusJDBC");
        }

        return buses;
    }

    @Override
    public void update(Bus entity) {
        String query = "UPDATE buses SET name = ?, number = ?, " +
                "places_num = ?, is_free = ?" +
                "WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in BusJDBC update");

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setInt(3, entity.getNumPlaces());
            preparedStatement.setBoolean(4, entity.getFree());
            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in BusJDBC update");
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in BusJDBC update");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_UPDATE") + "in BusJDBC");
        }
    }

    @Override
    public void delete(Bus entity) {
        String query = "DELETE FROM buses WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in BusJDBC deleting");

            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in BusJDBC deleting");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_DELETE") + "in BusJDBC");
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.debug(properties.getProperty("CONN_CLOSE") + "in BusJDBC");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_CLOSE_CONN") + "in BusJDBC");
        }
    }
}
