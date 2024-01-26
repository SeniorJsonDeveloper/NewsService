package project.newtrying.service;

import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;

import java.util.List;

public interface UserService {
    User createUserWithNews(User user, List<News> news);
    List<User> findAll();
    User findById(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(Long id);
}
