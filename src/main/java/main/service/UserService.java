package main.service;

import main.model.User;

public interface UserService {
    public User getByName(String login);
}
