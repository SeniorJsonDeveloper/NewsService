package project.newtrying.models.dto.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserListResponse {
    private List<UserResponse> userResponseList = new ArrayList<>();

}
