package blog.blog_api.controller;

import blog.blog_api.service.TagService;
import org.springframework.web.bind.annotation.PathVariable;


import blog.blog_api.DTO.BlogDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Tag(name = "Tags", description = "Gestion des tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<BlogDTO.TagResponse> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO.TagResponse createTag(@Valid @RequestBody BlogDTO.TagRequest request) {
        return tagService.createTag(request);
    }

    @PostMapping("/{tagId}/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addTagToPost(@PathVariable Long postId, @PathVariable Long tagId) {
        tagService.addTagToPost(postId, tagId);
    }
}