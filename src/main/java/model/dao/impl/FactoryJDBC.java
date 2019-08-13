package model.dao.impl;

import model.dao.*;
import model.entity.Bus;
import model.entity.Direction;
import model.entity.Request;
import model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class FactoryJDBC extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserJDBC(getConnection());
    }

    @Override
    public DirectionDao createDirectionDao() {
        return new DirectionJDBC(getConnection());
    }

    @Override
    public RequestDao createRequestDao() {
        return new RequestJDBC(getConnection());
    }

    @Override
    public BusDao createBusDao() {
        return new BusJDBC(getConnection());
    }

    private Connection getConnection(){
        try {
            return ConnectionPool.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
