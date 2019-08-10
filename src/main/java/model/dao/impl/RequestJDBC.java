package model.dao.impl;

import model.dao.Dao;
import model.dao.RequestDao;
import model.entity.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RequestJDBC extends JDBC implements RequestDao {
    public RequestJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Request entity) {
        String query = "INSERT INTO requests(user_id, direction_id, bus_id, is_accept) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.info(properties.getProperty("PREP_STAT_OPEN") + "in RequestJDBC creating");

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getDirectionId());
            preparedStatement.setInt(3, entity.getBusId());
            preparedStatement.setBoolean(4, entity.getIsAccept());

            preparedStatement.execute();

            log.info(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in RequestJDBC creating");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(properties.getProperty("SQL_EXC_WHILE_CREATE") + "in RequestJDBC creating");
        }
    }

    @Override
    public List<Request> readAll() {
        return null;
    }

    @Override
    public void update(Request entity) {
        String query = "UPDATE requests SET user_id = ?, direction_id = ?, " +
                "bus_id = ?, is_accept = ?" +
                "WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in RequestJDBC update");

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getDirectionId());
            preparedStatement.setInt(3, entity.getBusId());
            preparedStatement.setBoolean(4, entity.getIsAccept());
            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in RequestJDBC update");
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in RequestJDBC update");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_UPDATE") + "in RequestJDBC");
        }
    }

    @Override
    public void delete(Request entity) {
        String query = "DELETE FROM requests WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in RequestJDBC deleting");

            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in RequestJDBC deleting");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_DELETE") + "in RequestJDBC");
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
            log.debug(properties.getProperty("CONN_CLOSE") + "in RequestJDBC");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_CLOSE_CONN") + "in RequestJDBC");
        }
    }
}
