package com.shlagoverflow.api.service.impl;

import com.shlagoverflow.api.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Leboc Philippe.
 */
@SuppressWarnings("unchecked")
public abstract class DefaultImpl<T, K extends MongoRepository> implements DefaultService<T> {

    @Autowired
    private K repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findOneById(String id) {
        return (T) repository.findOne(id);
    }

    @Override
    public T create(T object) {
        return (T) repository.insert(object);
    }

    @Override
    public boolean update(T obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        repository.delete(id);
        return true;
    }
}
