package blog.blog_api.controller;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@Tag(name = "Posts", description = "Gestion des posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<BlogDTO.PostResponse> getAllPosts(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content){
        return postService.getAllPosts(userId,title,content);
    }


    @GetMapping("{id}")
    public BlogDTO.PostResponse getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO.PostResponse createPost(@Valid @RequestBody BlogDTO.PostRequest request){
        return postService.createPost(request);
    }


    @PutMapping("{id}")
    public BlogDTO.PostResponse updatePost(@PathVariable Long id,@Valid @RequestBody BlogDTO.PostRequest request){
        return postService.updatePost(id, request);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

}
