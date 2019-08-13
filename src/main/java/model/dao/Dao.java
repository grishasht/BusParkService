package model.dao;

import java.util.List;

public interface Dao<E> extends AutoCloseable{
    void create(E entity);

    List<E> readAll();

    void update(E entity);

    void delete(E entity);
}
