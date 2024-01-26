package project.newtrying.service.impl;

import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.repository.UserRepository;
import project.newtrying.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    @Override
    public User createUserWithNews(User user, List<News> news) {
        return null;
    }

    @Override
    @NotEmpty
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException(""));
    }

    @Override
    public User createUser(User user) {
        return repository.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return repository.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
