package com.shlagoverflow.api.service;

import java.util.List;
import java.util.Optional;

/**
 * @author Leboc Philippe.
 */
public interface DefaultService<T> {
    List<T> findAll();
    T findOneById(String id);
    T create(T object);
    boolean update(T obj);
    boolean delete(String id);
}
