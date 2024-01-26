package project.newtrying.models.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import project.newtrying.models.entitites.News;
import project.newtrying.models.dto.UpsertNewsRequest;
import project.newtrying.models.dto.responses.NewsListResponse;
import project.newtrying.models.dto.responses.NewsResponse;
import project.newtrying.models.mappers.NewsMapper;
import project.newtrying.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NewsMapperImpl implements NewsMapper {

    private final UserService dataBaseUserServiceImpl;

    public News requestToNews(UpsertNewsRequest request) {
        News news1 = new News();
        news1.setNewsName(request.getNewsName());
        news1.setAuthor(request.getAuthor());
        news1.setCategory(request.getCategory());
        news1.setComments(request.getComments());
        news1.setUser(dataBaseUserServiceImpl.findById(request.getUserId()));
        return news1;
    }

    public News requestToNews(Long id, UpsertNewsRequest request) {
        News news = requestToNews(request);
        news.setId(id);
        return news;
    }

    public NewsResponse response(News news) {
        NewsResponse response = new NewsResponse();
        response.setNewsId(news.getId());
        response.setNewsName(news.getNewsName());
        response.setAuthor(news.getAuthor());
        response.setCategory(news.getCategory());
        response.setComments(news.getComments());
        return response;
    }

    @Override
    public NewsListResponse responseList(List<News> news) {
        return null;
    }

    public List<NewsResponse> responseToList(List<News> news) {
        return news.stream()
                .map(this::response)
                .collect(Collectors.toList());
    }

    public NewsListResponse listToResponse(List<News> newsList) {
        NewsListResponse newsListResponse = new NewsListResponse();
        newsListResponse.setNewsResponseList(newsList
                .stream()
                .map(this::response)
                .collect(Collectors.toList()));
        return newsListResponse;
    }
}

