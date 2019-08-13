package model.dao;

import model.entity.Request;

import java.util.List;

public interface RequestDao extends Dao<Request> {

    List<Request> readByUserId(int userId);

    void setAccept(Integer id);
}
