package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Comment;
import blog.blog_api.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public List<BlogDTO.CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(BlogDTO::toCommentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.CommentResponse createComment(BlogDTO.CommentRequest request){
            Comment comment = BlogDTO.toCommentModel(request);
            return BlogDTO.toCommentResponse(commentRepository.save(comment));
    }


    @Override
    public void deleteComment(Long id){
        if(!commentRepository.existsById(id)){
            throw  new RuntimeException("Commentaire non trouve !");
        }
        commentRepository.deleteById(id);
    }
}
