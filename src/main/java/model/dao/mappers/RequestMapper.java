package model.dao.mappers;

import model.entity.Request;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestMapper implements Mapper<Request> {
    @Override
    public Request getEntity(ResultSet resultSet, int... index) {
        Request request = new Request();

        try {
            request.setId(resultSet.getInt(index[0]));
            request.setName(resultSet.getString(index[1]));
            request.setNumber(resultSet.getString(index[2]));
            request.setNumPlaces(resultSet.getInt(index[3]));
            request.setStart(resultSet.getString(index[4]));
            request.setEnd(resultSet.getString(index[5]));
            request.setDistance(resultSet.getInt(index[6]));
            request.setIsAccept(resultSet.getBoolean(index[7]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return request;
    }
}
