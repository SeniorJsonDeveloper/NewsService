package project.newtrying.models.mappers;

import project.newtrying.models.entitites.News;
import project.newtrying.models.dto.UpsertNewsRequest;
import project.newtrying.service.UserService;


public abstract class NewsMapperDelegate implements NewsMapper {

    private  UserService userService;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setNewsName(news.getNewsName());
        news.setAuthor(news.getAuthor());
        news.setCategory(news.getCategory());
        news.setUser(userService.findById(news.getUser().getId()));
        return news;
    }

    @Override
    public News requestToNews(Long id, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(id);
        return news;
    }
}
