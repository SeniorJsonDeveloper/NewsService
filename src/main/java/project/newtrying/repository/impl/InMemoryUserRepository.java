package project.newtrying.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.newtrying.repository.NewsRepository;
import project.newtrying.repository.UserRepository;
import project.newtrying.exception.NotFoundException;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.utils.BeanUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long,User> repository = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);
    private NewsRepository newsRepository;
    @Autowired
    @Lazy
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    @Override
    public List<User> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(repository.get(name));
    }

    @Override
    public User createUser(User user) {
        Long userId = currentId.getAndIncrement();
        user.setId(userId);
        user.setUserName(user.getUserName());
        repository.put(userId,user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        Long userId = user.getId();
        User existedUser = repository.get(userId);
        if (existedUser==null){
            throw new NotFoundException
                    (MessageFormat.format("Пользователь с указанным {} id не найден!",userId));
        }
        BeanUtils.copyNonNullProperties(user,existedUser);
        user.setId(userId);
        repository.put(userId,existedUser);
        return existedUser;
    }

    @Override
    public void deleteById(Long id) {
        User user = repository.get(id);
        if (user == null){
            throw new NotFoundException
                    (MessageFormat.format("Пользователь с указанным {} id не найден!",user));

        }
        newsRepository.deleteByIds(user.getNewsList().
                stream()
                .map(News::getId)
                .collect(Collectors.toList()));

    }
}
