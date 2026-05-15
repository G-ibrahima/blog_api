package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    List<Comment> comments = new ArrayList<>();

    @Override
    public List<BlogDTO.CommentResponse> getCommentsByPostId(Long postId){
        return comments.stream()
                .filter(u->u.getPostId().equals(postId))
                .map(BlogDTO::toCommentResponse).collect(Collectors.toList());
    }

    @Override
    public BlogDTO.CommentResponse createComment(BlogDTO.CommentRequest request){
            Comment comment = BlogDTO.toCommentModel(request);
            comment.setCommentId((long) comments.size() + 1);
            comments.add(comment);
            return BlogDTO.toCommentResponse(comment);
    }


    @Override
    public void deleteComment(Long id){
        boolean exists = comments.removeIf(u -> u.getPostId().equals(id));
        if(!exists) throw  new RuntimeException("Commentaire non trouve !");
    }
}
