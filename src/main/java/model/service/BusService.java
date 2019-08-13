package model.service;

import model.dao.BusDao;
import model.dao.DaoFactory;
import model.entity.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static BusDao busDao = daoFactory.createBusDao();

    public List<Bus> getBuses(){
        return busDao.readAll();
    }

    public Integer getMaxRows(){
        return busDao.getMaxRows();
    }

    public List<List<Bus>> getButchDirections(int num){
        List<Bus> buses = busDao.readAll();

        return butchDirections(buses, num);
    }

    private List<List<Bus>> butchDirections(List<Bus> buses, int limit){
        List<List<Bus>> lists = new ArrayList<>();

        int i = 1;
        int k = 0;
        lists.add(new ArrayList<>());
        for (Bus direction : buses){
            if (i % limit != 0){
                lists.get(k).add(direction);
            } else {
                lists.get(k).add(direction);
                lists.add(new ArrayList<>());
                k++;
            }
            i++;
        }

        return lists;
    }

    public void setFree(Boolean isFre, Bus entity, int userId){
        Bus bus = new Bus();

        bus.setId(entity.getId());
        bus.setName(entity.getName());
        bus.setFree(isFre);
        bus.setNumber(entity.getNumber());
        bus.setNumPlaces(entity.getNumPlaces());
        bus.setUserId(userId);

        busDao.update(bus);
    }

    public Bus readById(int id){
        return busDao.readById(id);
    }
}
