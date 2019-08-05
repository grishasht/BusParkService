package model.dao;

import model.dao.impl.FactoryJDBC;
import model.entity.Bus;
import model.entity.Direction;
import model.entity.Request;
import model.entity.User;

public abstract class DaoFactory {

    public abstract Dao<User> createUserDao();

    public abstract Dao<Direction> createDirectionDao();

    public abstract Dao<Request> createRequestDao();

    public abstract Dao<Bus> createBusDao();

    private static class SingletonHolder {
        static final DaoFactory INSTANCE = new FactoryJDBC();
    }

    public static DaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
