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

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseUserServiceImpl implements UserService {
    private final DataBaseUserRepository dataBaseUserRepository;
    private final DataBaseNewsRepository dataBaseNewsRepository;

    @Override
    @Transactional
    public User createUserWithNews(User user, List<News> newsList) {
        User existedUser = dataBaseUserRepository.save(user);
        for (News news:newsList){
            news.setUser(existedUser);
            var savedNews = dataBaseNewsRepository.save(news);
            existedUser.addNews(savedNews);
        }
        return existedUser;
    }

    @Override
    public List<User> findAll() {
        return dataBaseUserRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return dataBaseUserRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        MessageFormat.format("Пользователь с указанным {} не найден",id)
                ));
    }

    @Override
    public User findByName(String name) {
        return dataBaseUserRepository.findByName(name)
                .orElseThrow(()->new NotFoundException(""));
    }

    @Override
    public User createUser(User user) {
        return dataBaseUserRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user,existedUser);
        return dataBaseUserRepository.save(existedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        dataBaseUserRepository.deleteById(id);
    }
}
