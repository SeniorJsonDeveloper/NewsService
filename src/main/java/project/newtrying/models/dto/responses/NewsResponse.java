package project.newtrying.models.dto.responses;

import lombok.Data;

@Data
public class NewsResponse {
    private Long newsId;
    private String category;
    private String author;
    private String newsName;
    private String comments;

}
