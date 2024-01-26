package project.newtrying.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {
    List<NewsResponse> newsResponseList = new ArrayList<>();

}
