package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;

import java.util.List;

public interface TagService {
    List<BlogDTO.TagResponse> getAllTags();
    BlogDTO.TagResponse createTag(BlogDTO.TagRequest request);
    void addTagToPost(Long postId, Long tagId);
}