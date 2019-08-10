package model.service;

import model.dao.Dao;
import model.dao.DaoFactory;
import model.dao.DirectionDao;
import model.entity.Direction;

import java.util.List;

public class DirectionService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static DirectionDao directionDao = daoFactory.createDirectionDao();

    public List<Direction> getDirections(){
        return directionDao.readAll();
    }
}
