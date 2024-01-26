package project.newtrying.models.mappers;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.newtrying.models.entitites.News;
import project.newtrying.models.dto.UpsertNewsRequest;
import project.newtrying.models.dto.responses.NewsListResponse;
import project.newtrying.models.dto.responses.NewsResponse;

import java.util.List;
@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewsMapper {
    News requestToNews(UpsertNewsRequest request);
    News requestToNews(Long id,UpsertNewsRequest request);
    NewsResponse response(News news);
    NewsListResponse responseList(List<News> news);

}
