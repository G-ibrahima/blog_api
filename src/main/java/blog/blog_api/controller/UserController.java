package blog.blog_api.controller;


import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<BlogDTO.UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public BlogDTO.UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO.UserResponse creatUser(@RequestBody BlogDTO.UserRequest  request) {
        return userService.createUser(request);
    }

    @PutMapping("{id}")
    public BlogDTO.UserResponse updateUser(@PathVariable Long id ,@RequestBody BlogDTO.UserRequest  request) {
        return userService.updateUser(id,request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
