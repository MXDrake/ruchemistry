package main.dao;

import main.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserDaoImpl extends AbstractDaoImpl implements  UserDao {
    @Override
    public User getByName(String name) {
        try {
            User user =  (User) entityManager.createQuery("FROM User WHERE name=:NAME").
                    setParameter("NAME",name).getSingleResult();
            return user;
        }
        catch (Exception e){
            return null;
        }

    }
}
