package model.dao.impl;

import model.dao.Dao;
import model.dao.DirectionDao;
import model.dao.mappers.DirectionMapper;
import model.dao.mappers.Mapper;
import model.entity.Direction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DirectionJDBC extends JDBC implements DirectionDao {
    public DirectionJDBC(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Direction entity) {
        String query = "INSERT INTO directions(start_p, end_p, distance, is_free) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.info(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC creating");

            preparedStatement.setString(1, entity.getStart());
            preparedStatement.setString(2, entity.getEnd());
            preparedStatement.setInt(3, entity.getDistance());
            preparedStatement.setBoolean(4, entity.getIsFree());

            preparedStatement.execute();

            log.info(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in DirectionJDBC creating");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error(properties.getProperty("SQL_EXC_WHILE_CREATE") + "in DirectionJDBC creating");
        }
    }

    @Override
    public List<Direction> readAll() {
        List<Direction> directions = new LinkedList<>();
        String query = "SELECT * FROM directions";
        Mapper directionMapper = new DirectionMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC readAll");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in DirectionJDBC readAll");

                while (resultSet.next()) {
                    directions.add((Direction) directionMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in DirectionJDBC");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in DirectionJDBC readAll");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in DirectionJDBC");
        }

        return directions;
    }

    @Override
    public Direction readById(int direction_id) {
        Direction direction = null;
        String query = "SELECT * FROM directions where id =" + direction_id;
        Mapper directionMapper = new DirectionMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC readByID");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in DirectionJDBC readById");

                while (resultSet.next()) {
                    direction = ((Direction) directionMapper.getEntity(resultSet, 1, 2, 3, 4, 5, 6));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in DirectionJDBC");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in DirectionJDBC readByID");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in DirectionJDBC");
        }

        return direction;
    }

    @Override
    public void update(Direction entity) {
        String query = "UPDATE directions SET is_free = ?, bus_id = ?"
                + " WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC update");

            preparedStatement.setBoolean(1, entity.getIsFree());
            preparedStatement.setInt(2, entity.getBusId());
            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in DirectionJDBC update");
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in DirectionJDBC update");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_UPDATE") + "in DirectionJDBC");
        }
    }

    @Override
    public void delete(Direction entity) {
        String query = "DELETE FROM directions WHERE id = " + entity.getId();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC deleting");

            preparedStatement.execute();

            log.debug(properties.getProperty("SUCCESS_QUERY_EXECUTE") + "in DirectionJDBC deleting");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_DELETE") + "in DirectionJDBC");
        }
    }

    @Override
    public Integer getMaxRows() {
        int max = 0;
        String query = "SELECT directions.*,\n" +
                "       row_number() OVER () as rnum\n" +
                "FROM directions order by rnum desc limit 1;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    max = resultSet.getInt(7);
                }
            }

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in DirectionJDBC");
        }

        return max;
    }

    @Override
    public List<Direction> readFew(int num, int minVal) {
        List<Direction> directions = new LinkedList<>();
        String query = "select *\n" +
                "from directions\n" +
                "where id > " + minVal + " order by id\n" +
                "limit " + num;
        Mapper directionMapper = new DirectionMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            log.debug(properties.getProperty("PREP_STAT_OPEN") + "in DirectionJDBC readFew");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                log.debug(properties.getProperty("RES_SET_OPEN") + "in DirectionJDBC readFew");

                while (resultSet.next()) {
                    directions.add((Direction) directionMapper.getEntity(resultSet, 1, 2, 3, 4, 5));
                }
                log.debug(properties.getProperty("RES_SET_CLOSE") + "in DirectionJDBC");
            }
            log.debug(properties.getProperty("PREP_STAT_CLOSE") + "in DirectionJDBC readFew");

        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_READ") + "in DirectionJDBC");
        }

        return directions;
    }

    @Override
    public void close() {
        try {
            connection.close();
            log.debug(properties.getProperty("CONN_CLOSE") + "in DirectionJDBC");
        } catch (SQLException e) {
            log.error(properties.getProperty("SQL_EXC_WHILE_CLOSE_CONN") + "in DirectionJDBC");
        }
    }
}
