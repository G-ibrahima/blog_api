package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;

import java.util.List;

public interface UserService {
    List<BlogDTO.UserResponse> getAllUsers();
    BlogDTO.UserResponse getUserById(Long id);
    BlogDTO.UserResponse createUser(BlogDTO.UserRequest request);
    BlogDTO.UserResponse updateUser(Long id, BlogDTO.UserRequest request);
    void deleteUser(Long id);
}