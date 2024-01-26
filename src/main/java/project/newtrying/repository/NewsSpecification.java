package project.newtrying.repository;

import org.springframework.data.jpa.domain.Specification;
import project.newtrying.models.entitites.News;
import project.newtrying.models.filter.NewsFilter;

public interface NewsSpecification {
    static Specification<News> withFilter(NewsFilter newsFilter){
        return Specification.where(byAuthor(newsFilter.getAuthor()))
                .and(byCategory(newsFilter.getCategory()));
    }
    static Specification<News> byAuthor(String author){
        return (root, query, criteriaBuilder) -> {
            if (author==null){
                return null;
            }
            else {
                return criteriaBuilder.equal(root.get("author"),author);
            }
        };
    }
    static Specification<News> byCategory(String category){
        return (root, query, criteriaBuilder) -> {
            if (category==null){
                return null;
            }
            else {
                return criteriaBuilder.equal(root.get("category"),category);
            }
        };
    }
}
