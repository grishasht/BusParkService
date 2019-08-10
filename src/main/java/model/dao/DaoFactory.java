package model.dao;

import model.dao.impl.FactoryJDBC;
import model.entity.Bus;
import model.entity.Direction;
import model.entity.Request;
import model.entity.User;

public abstract class DaoFactory {

    public abstract UserDao createUserDao();

    public abstract DirectionDao createDirectionDao();

    public abstract RequestDao createRequestDao();

    public abstract BusDao createBusDao();

    private static class SingletonHolder {
        static final DaoFactory INSTANCE = new FactoryJDBC();
    }

    public static DaoFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
