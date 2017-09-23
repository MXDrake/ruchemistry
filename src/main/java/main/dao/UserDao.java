package main.dao;

import main.model.User;

public interface UserDao {
    public User getByName(String login);

}
