package model.dao.impl;

import model.dao.Dao;
import model.entity.User;

import java.util.List;

public class UserJDBC implements Dao<User> {
    @Override
    public void create(User entity) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User readById(int id) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
