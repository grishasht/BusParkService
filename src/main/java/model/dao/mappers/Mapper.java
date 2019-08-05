package model.dao.mappers;

import java.sql.ResultSet;

public interface Mapper<E> {
    E getEntity(ResultSet resultSet, int... index);
}
