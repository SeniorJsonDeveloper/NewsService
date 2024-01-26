package project.newtrying.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpsertNewsRequest {
    private Long userId;
    private String newsName;
    private String author;
    private String category;
    private String comments;
}
