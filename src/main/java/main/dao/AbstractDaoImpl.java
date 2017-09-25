package main.dao;

import main.model.Role;
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

    public void update(Object model) {

        entityManager.merge(model);
    }



}
