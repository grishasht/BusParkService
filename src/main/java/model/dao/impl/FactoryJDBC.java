package model.dao.impl;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.entity.Bus;
import model.entity.Direction;
import model.entity.Request;
import model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class FactoryJDBC extends DaoFactory {

    @Override
    public Dao<User> createUserDao() {
        return new UserJDBC(getConnection());
    }

    @Override
    public Dao<Direction> createDirectionDao() {
        return new DirectionJDBC(getConnection());
    }

    @Override
    public Dao<Request> createRequestDao() {
        return new RequestJDBC(getConnection());
    }

    @Override
    public Dao<Bus> createBusDao() {
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
