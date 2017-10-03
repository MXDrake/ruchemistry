package main.dao;

import main.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AbstractDaoImpl<T> implements AbstractDao {
    @PersistenceContext
    EntityManager entityManager;

    public void addModel(Object model){
        try {
            entityManager.persist(model);
        }
        catch (Exception e){
            System.out.println("Ошибка создания обьекта");
        }
    }

    public void update(Object model) {
        try {
            entityManager.merge(model);
        }
        catch (Exception e){
            System.out.println("Ошибка обновления обьекта");
        }
    }
}
