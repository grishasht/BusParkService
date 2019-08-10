package model.dao.mappers;

import model.entity.Bus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusMapper implements Mapper<Bus> {
    @Override
    public Bus getEntity(ResultSet resultSet, int... index) {
        Bus bus = new Bus();

        try {
            bus.setId(resultSet.getInt(index[0]));
            bus.setName(resultSet.getString(index[1]));
            bus.setNumber(resultSet.getString(index[2]));
            bus.setNumPlaces(resultSet.getInt(index[3]));
            bus.setFree(resultSet.getBoolean(index[4]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bus;

    }
}
