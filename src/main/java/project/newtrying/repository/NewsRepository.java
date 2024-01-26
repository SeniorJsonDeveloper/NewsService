package project.newtrying.repository;

import project.newtrying.models.entitites.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository {
    List<News> findAll();
    Optional<News> findById(Long id);
    News createNews(News news);
    News updateNews(News news);
    void deleteById(Long id);
    void deleteByIds(List<Long> ids);
}
