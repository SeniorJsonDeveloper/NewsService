package project.newtrying.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.newtrying.models.dto.UpsertNewsRequest;
import project.newtrying.models.filter.NewsFilter;
import project.newtrying.models.mappers.impl.NewsMapperImpl;
import project.newtrying.models.dto.responses.NewsListResponse;
import project.newtrying.models.dto.responses.NewsResponse;
import project.newtrying.service.NewsService;


import java.util.Objects;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService dataBaseNewsServiceImpl;
    private final NewsMapperImpl newsMapper;
    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> getWithFilter(@RequestParam String category,
                                                          @RequestParam String author){
        NewsFilter newsFilter = new NewsFilter();
        newsFilter.setCategory(category);
        newsFilter.setAuthor(author);
        return ResponseEntity.ok(newsMapper.listToResponse(dataBaseNewsServiceImpl.newsByFilter(newsFilter)));

    }
    @GetMapping
    public ResponseEntity<NewsListResponse> getAllNews(){
        return ResponseEntity.ok(newsMapper.listToResponse(dataBaseNewsServiceImpl.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.response(dataBaseNewsServiceImpl.findById(id)));
    }
    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody UpsertNewsRequest request) {
        var existedNews = dataBaseNewsServiceImpl.createNews(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.response(existedNews));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@PathVariable("id")Long id,
                                                   @RequestBody UpsertNewsRequest request){
        var existedNews = dataBaseNewsServiceImpl.updateNews(newsMapper.requestToNews(id,request));
        return ResponseEntity.ok(newsMapper.response(dataBaseNewsServiceImpl.updateNews(existedNews)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id){
        dataBaseNewsServiceImpl.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
