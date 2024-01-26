package project.newtrying.models.responses;

import lombok.Data;

@Data
public class NewsResponse {
    private Long newsId;
    private String category;
    private String author;
    private String newsName;
    private String comments;

}
