package project.newtrying.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import project.newtrying.models.entitites.News;
import project.newtrying.models.filter.NewsFilter;

import java.util.List;


public interface DataBaseNewsRepository extends JpaRepository<News,Long>, JpaSpecificationExecutor<News> {
    Page<News>findAllByCategory(String category, Pageable pageable);

}
