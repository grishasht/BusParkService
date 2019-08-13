package model.service;

import model.dao.DaoFactory;
import model.dao.RequestDao;
import model.entity.Request;
import model.entity.User;

import java.util.List;

public class RequestService {
    private static DaoFactory daoFactory = DaoFactory.getInstance();
    private static RequestDao requestDao = daoFactory.createRequestDao();

    public void insertRequest(Request entity){
        requestDao.create(entity);
    }

    public List<Request> getRequests(User user){
        return requestDao.readByUserId(user.getId());
    }
}
