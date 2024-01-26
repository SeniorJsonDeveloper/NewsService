package project.newtrying.service;

import project.newtrying.models.entitites.News;
import project.newtrying.models.filter.NewsFilter;

import java.util.List;

public interface NewsService {
    List<News> newsByFilter(NewsFilter newsFilter);
    List<News> findAll();
    News findById(Long id);
    News createNews(News news);
    News updateNews(News news);
    void deleteById(Long id);
    void deleteByIds(List<Long> ids);
}
