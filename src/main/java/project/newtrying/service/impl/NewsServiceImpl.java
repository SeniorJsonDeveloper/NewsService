package project.newtrying.service.impl;

import jakarta.persistence.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.newtrying.models.entitites.News;
import project.newtrying.models.filter.NewsFilter;
import project.newtrying.repository.NewsRepository;
import project.newtrying.service.NewsService;
import org.apache.commons.lang3.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;



    @Override
    public List<News> newsByFilter(NewsFilter newsFilter) {
        return (List<News>) new NotImplementedException();
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
