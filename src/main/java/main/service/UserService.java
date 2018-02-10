package main.service;

import main.model.User;

public interface UserService {
    User getByName(String login);
    User getCurrentUser();
}
