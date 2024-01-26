package project.newtrying.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.exception.NotFoundException;
import project.newtrying.repository.DataBaseNewsRepository;
import project.newtrying.repository.DataBaseUserRepository;
import project.newtrying.service.UserService;
import project.newtrying.utils.BeanUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseUserServiceImpl implements UserService {
    private final DataBaseUserRepository repository;
    private final DataBaseNewsRepository newsRepository;

    @Override
    @Transactional
    public User createUserWithNews(User user, List<News> newsList) {
        User existedUser = repository.save(user);
        for (News news:newsList){
            news.setUser(existedUser);
            var savedNews = newsRepository.save(news);
            existedUser.addNews(savedNews);
        }
        return existedUser;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new NotFoundException(""));
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user,existedUser);
        return repository.save(existedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
}
