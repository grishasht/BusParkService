package model.service;

import model.dao.BusDao;
import model.dao.DaoFactory;
import model.entity.Bus;

import java.util.List;

public class BusService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static BusDao busDao = daoFactory.createBusDao();

    public List<Bus> getBuses(){
        return busDao.readAll();
    }
}
