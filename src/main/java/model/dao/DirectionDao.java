package model.dao;

import model.entity.Direction;

import java.util.List;

public interface DirectionDao extends Dao<Direction> {
    Integer getMaxRows();

    List<Direction> readFew(int num, int minVal);

    Direction readById(int direction_id);
}
