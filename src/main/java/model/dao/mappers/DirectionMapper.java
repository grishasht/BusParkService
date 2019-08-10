package model.dao.mappers;

import model.entity.Direction;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectionMapper implements Mapper<Direction> {
    @Override
    public Direction getEntity(ResultSet resultSet, int... index) {
        Direction direction = new Direction();

        try {
            direction.setId(resultSet.getInt(index[0]));
            direction.setStart(resultSet.getString(index[1]));
            direction.setEnd(resultSet.getString(index[2]));
            direction.setDistance(resultSet.getInt(index[3]));
            direction.setIsFree(resultSet.getBoolean(index[4]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return direction;
    }
}
