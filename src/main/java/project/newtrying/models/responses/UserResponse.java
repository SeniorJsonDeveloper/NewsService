package project.newtrying.models.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String userName;
    private List<NewsResponse> news = new ArrayList<>();

}
