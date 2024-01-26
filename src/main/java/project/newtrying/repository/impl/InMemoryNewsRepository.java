package project.newtrying.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.newtrying.repository.NewsRepository;
import project.newtrying.exception.NotFoundException;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.repository.UserRepository;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@RequiredArgsConstructor
public class InMemoryNewsRepository implements NewsRepository {
    private UserRepository userRepository;

    @Autowired
    @Lazy
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Map<Long,News> repository = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(1);

    @Override
    public List<News> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Optional<News> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public News createNews(News news) {
        Long newsId = currentId.getAndIncrement();
        Long userId = news.getUser().getId();
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException(""));
        news.setUser(user);
        news.setId(newsId);
        repository.put(newsId,news);
        user.addNews(news);
        userRepository.updateUser(user);
        return news;
    }

    @Override
    public News updateNews(News news) {
        Long newsId = news.getId();
        News currentNews = repository.get(newsId);
        if (currentNews==null){
                throw new NotFoundException
                        (MessageFormat.format("Пользователь с указанным {} id не найден!", newsId));

            }
        currentNews.setId(newsId);
        BeanUtils.copyProperties(news,currentNews);
        repository.put(newsId,currentNews);
        return currentNews;
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        ids.forEach(repository::remove);
    }
}
