package project.newtrying.models.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.newtrying.models.entitites.User;
import project.newtrying.models.dto.UpsertUserRequest;
import project.newtrying.models.responses.UserListResponse;
import project.newtrying.models.responses.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl  {

    private final NewsMapperImpl newsMapper;

    public User requestToUser(UpsertUserRequest request){
        User user = new User();
        user.setUserName(request.getUserName());
        return user;
    }
    public User requestToUser(Long id,UpsertUserRequest request){
        User user = requestToUser(request);
        user.setId(id);
        return user;
    }
    public UserResponse response(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setNews(newsMapper.responseList(user.getNewsList()));
        return response;
    }
    public UserListResponse listResponse(List<User> userList){
        UserListResponse response = new UserListResponse();
        response.setUserResponseList(userList
                .stream().map(this::response)
                .collect(Collectors.toList()));
        return response;
    }




}
