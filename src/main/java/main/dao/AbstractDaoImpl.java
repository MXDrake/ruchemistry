package main.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class AbstractDaoImpl<T> implements AbstractDao {
    @PersistenceContext
    EntityManager entityManager;

    public void addModel(Object model){
        entityManager.persist(model);
    }



}
