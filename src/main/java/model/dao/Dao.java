package model.dao;

import java.util.List;

public interface Dao<E> extends AutoCloseable{
    void create(E entity);

    List<E> readAll();

    E readById(int id);

    void update(E entity);

    void delete(E entity);
}
