package main.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public interface AbstractDao <T> {

    public void addModel(T model);
    public void update(T model);
}
