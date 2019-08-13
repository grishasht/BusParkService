package model.service;

import model.dao.DaoFactory;
import model.dao.DirectionDao;
import model.entity.Direction;

import java.util.ArrayList;
import java.util.List;

public class DirectionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private DirectionDao directionDao = daoFactory.createDirectionDao();

    public List<Direction> getDirections(){
        return directionDao.readAll();
    }

    public Integer getMaxRows(){
        return directionDao.getMaxRows();
    }

    public List<List<Direction>> getButchDirections(int num){
        List<Direction> directions = directionDao.readAll();

        return butchDirections(directions, num);
    }

    private List<List<Direction>> butchDirections(List<Direction> directions, int limit){
        List<List<Direction>> lists = new ArrayList<>();

        int i = 1;
        int k = 0;
        lists.add(new ArrayList<>());
        for (Direction direction : directions){
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

    public Direction readById(int direction_id) {
        return directionDao.readById(direction_id);
    }

    public void setFree(Boolean isFree, Direction entity){
        Direction direction = new Direction();
        direction.setId(entity.getId());
        direction.setIsFree(isFree);
        direction.setBusId(entity.getBusId());

        directionDao.update(direction);
    }
}
