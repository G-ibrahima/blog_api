package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;

import java.util.List;

public interface PostService {
    List<BlogDTO.PostResponse> getAllPosts();
    BlogDTO.PostResponse getPostById(Long id);
    BlogDTO.PostResponse createPost(BlogDTO.PostRequest request);
    BlogDTO.PostResponse updatePost(Long id, BlogDTO.PostRequest request);
    void deletePost(Long id);
}