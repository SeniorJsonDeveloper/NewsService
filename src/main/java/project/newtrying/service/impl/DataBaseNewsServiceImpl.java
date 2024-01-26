package project.newtrying.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.exception.NotFoundException;
import project.newtrying.models.filter.NewsFilter;
import project.newtrying.repository.DataBaseNewsRepository;
import project.newtrying.repository.DataBaseUserRepository;
import project.newtrying.repository.NewsSpecification;
import project.newtrying.service.NewsService;
import project.newtrying.service.UserService;
import project.newtrying.utils.BeanUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataBaseNewsServiceImpl implements NewsService {
    private final DataBaseNewsRepository repository;
    private UserService userService;

    @Override
    public List<News> newsByFilter(NewsFilter newsFilter) {
       return repository.findAll(NewsSpecification.withFilter(newsFilter),
               PageRequest.of(newsFilter.getPageSize(),
                       newsFilter.getPageNumber())).getContent();
    }

    @Override
    public List<News> findAll() {
        return repository.findAll();
    }

    @Override
    public News findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()->new NotFoundException(""));
    }

    @Override
    public News createNews(News news) {
        User user = userService.findById(news.getUser().getId());
        news.setUser(user);
        return repository.save(news);
    }

    @Override
    @Transactional
    public News updateNews(News news) {
        User user = userService.findById(news.getUser().getId());
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news,existedNews);
        existedNews.setUser(user);
        return repository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);

    }

    @Override
    @Transactional
    public void deleteByIds(List<Long> ids) {
    repository.deleteAllById(ids);
    }
}
