package model.dao;

import model.entity.Bus;

public interface BusDao extends Dao<Bus> {

    Integer getMaxRows();

    Bus readById(int id);
}
