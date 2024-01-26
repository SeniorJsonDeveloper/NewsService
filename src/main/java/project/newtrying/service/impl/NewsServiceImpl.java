package project.newtrying.service.impl;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import project.newtrying.exception.NotFoundException;
import project.newtrying.models.entitites.News;
import project.newtrying.models.filter.NewsFilter;
import project.newtrying.repository.NewsRepository;
import project.newtrying.service.NewsService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;


    @Override
    public List<News> newsByFilter(NewsFilter newsFilter) {
        return new ArrayList<>();
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(
                id).orElseThrow(() -> new RuntimeException(""));
    }


    @Override
    public News createNews(News news) {
        return newsRepository.createNews(news);

//        Long newsId = news.getId();
//        Long userId = news.getUser().getId();

    }

    @Override
    public News updateNews(News news) {
        return newsRepository.updateNews(news);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        newsRepository.deleteByIds(ids);
    }
}
