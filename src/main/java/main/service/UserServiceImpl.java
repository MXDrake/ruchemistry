package main.service;

import main.dao.UserDao;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User getByName(String login) {
        return userDao.getByName(login);
    }
}
