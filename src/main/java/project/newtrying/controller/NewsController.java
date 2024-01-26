package project.newtrying.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.newtrying.models.dto.UpsertNewsRequest;
import project.newtrying.models.filter.NewsFilter;
import project.newtrying.models.mappers.impl.NewsMapperImpl;
import project.newtrying.models.dto.responses.NewsListResponse;
import project.newtrying.models.dto.responses.NewsResponse;
import project.newtrying.service.NewsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/news")
public class NewsController {
    private final NewsService newsService;
    private final NewsMapperImpl newsMapper;
    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> getWithFilter(@RequestParam String category){
        NewsFilter newsFilter = new NewsFilter();
        newsFilter.setCategory(category);
        return ResponseEntity.ok(newsMapper.listToResponse(newsService.newsByFilter(
                newsFilter
        )));
    }
    @GetMapping
    public ResponseEntity<NewsListResponse> getAllNews(){
        return ResponseEntity.ok(newsMapper.listToResponse(newsService.findAll()));
    }
    @GetMapping("{id}")
    public ResponseEntity<NewsResponse> getNewsById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.response(newsService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody UpsertNewsRequest request) {
        var existedNews = newsService.createNews(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.response(existedNews));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@PathVariable("id")Long id,
                                                   @RequestBody UpsertNewsRequest request){
        var existedNews = newsService.updateNews(newsMapper.requestToNews(id,request));
        return ResponseEntity.ok(newsMapper.response(newsService
                .updateNews(existedNews)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id){
        newsService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
