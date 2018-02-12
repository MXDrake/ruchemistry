package main.service;

import main.dao.UserDao;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByName(String login) {
        return userRepository.findAllByName(login);
    }

    @Override
    public User getCurrentUser() {
        try{
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }

    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
