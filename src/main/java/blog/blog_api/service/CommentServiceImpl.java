package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Comment;
import blog.blog_api.model.Post;
import blog.blog_api.repository.CommentRepository;
import blog.blog_api.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public List<BlogDTO.CommentResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(BlogDTO::toCommentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BlogDTO.CommentResponse createComment(BlogDTO.CommentRequest request){
            Post post = postRepository.findById(request.getPostId())
                    .orElseThrow(() -> new RuntimeException("User non trouvé !"));
            Comment comment = BlogDTO.toCommentModel(request);
            comment.setPost(post);
            return BlogDTO.toCommentResponse(commentRepository.save(comment));
    }


    @Override
    @Transactional
    public void deleteComment(Long id){
        if(!commentRepository.existsById(id)){
            throw  new RuntimeException("Commentaire non trouve !");
        }
        commentRepository.deleteById(id);
    }
}
