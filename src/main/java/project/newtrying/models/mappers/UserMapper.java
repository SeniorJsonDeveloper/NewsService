package project.newtrying.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import project.newtrying.models.entitites.User;
import project.newtrying.models.dto.UpsertUserRequest;
import project.newtrying.models.dto.responses.UserListResponse;
import project.newtrying.models.dto.responses.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,uses = NewsMapperDelegate.class)
public interface UserMapper {
    User requestToUser(UpsertUserRequest request);
    @Mapping(source = "id",target = "id")
    User requestToUser(Long id,UpsertUserRequest request);
    UserResponse response(User user);
    UserListResponse responseList(List<User> userList);
}
