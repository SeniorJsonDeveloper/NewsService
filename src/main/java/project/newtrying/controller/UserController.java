package project.newtrying.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.newtrying.models.dto.CreateUserWithNewsRequest;
import project.newtrying.models.dto.UpsertUserRequest;
import project.newtrying.models.entitites.News;
import project.newtrying.models.entitites.User;
import project.newtrying.models.mappers.impl.UserMapperImpl;
import project.newtrying.models.responses.UserListResponse;
import project.newtrying.models.responses.UserResponse;
import project.newtrying.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    @Qualifier("dataBaseUserServiceImpl")
    private final UserService userService;
    private final UserMapperImpl userMapper;


    @GetMapping
    public ResponseEntity<UserListResponse> getAllUsers(){
        return ResponseEntity.ok(userMapper.listResponse(userService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.response(userService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UpsertUserRequest request) {
        var existedUser = userService.createUser(userMapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.response(existedUser));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
                                                   @RequestBody UpsertUserRequest request) {
        var newUser = userService.updateUser(userMapper.requestToUser(id, request));
        return ResponseEntity.ok(userMapper.response(userService.createUser(newUser)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("save-with-news")
    public ResponseEntity<UserResponse> saveWithNews(@RequestBody CreateUserWithNewsRequest user){
        User requireUser = User.builder().userName(user.getUserName()).build();
        List<News> newsList = user.getNewsRequests()
                .stream()
                .map(newsRequest -> News.builder()
                        .newsName(newsRequest.getNewsName())
                        .category(newsRequest.getCategory()).build())
                .toList();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper
                        .response(
                                userService
                                        .createUserWithNews(requireUser,
                                                newsList)
                        ));


    }

}
