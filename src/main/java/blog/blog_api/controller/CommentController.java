package blog.blog_api.controller;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.service.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comments", description = "Gestion des commentaires")
public class CommentController {

    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("post/{postId}")
    public List<BlogDTO.CommentResponse> getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogDTO.CommentResponse createComment(@Valid @RequestBody BlogDTO.CommentRequest request){
        return commentService.createComment(request);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
    }
}
