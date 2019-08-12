package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> getDrivers();

    User readById(int id);
}
