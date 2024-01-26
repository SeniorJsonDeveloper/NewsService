package project.newtrying.models.entitites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<News> newsList = new ArrayList<>();

    public void addNews(News news) {
        if (news == null) newsList = new ArrayList<>();
        newsList.add(news);
    }

    public void removeNews(Long newsId) {
        newsList = newsList.stream().filter(n -> !n.getId().equals(newsId))
                .collect(Collectors.toList());
    }

    public List<News> getNewsList() {
        if (newsList == null) {
            newsList = new ArrayList<>();
        }
        return newsList;
    }




}
