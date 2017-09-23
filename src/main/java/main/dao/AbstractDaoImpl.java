package main.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

@Transactional
public class AbstractDao<T> {
    @PersistenceContext
    EntityManager entityManager;


}
