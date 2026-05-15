package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;

import java.util.List;

public interface CommentService {
    List<BlogDTO.CommentResponse> getCommentsByPostId(Long postId);
    BlogDTO.CommentResponse createComment(BlogDTO.CommentRequest request);
    void deleteComment(Long id);
}