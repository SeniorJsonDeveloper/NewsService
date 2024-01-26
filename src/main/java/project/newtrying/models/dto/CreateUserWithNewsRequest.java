package project.newtrying.models.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserWithNewsRequest {
    String userName;
    List<NewsRequest> newsRequests = new ArrayList<>();

}
