package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    List<Post> posts = new ArrayList<>();

    @Override
    public List<BlogDTO.PostResponse> getAllPosts() {
        return posts.stream().
                map(BlogDTO::toPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.PostResponse getPostById(Long id) {
        return posts.stream()
                .filter(u -> u.getPostId().equals(id))
                .map(BlogDTO::toPostResponse)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post non trouvé !"));
    }

    @Override
    public BlogDTO.PostResponse createPost(BlogDTO.PostRequest postRequest) {
        Post post = BlogDTO.toPostModel(postRequest);
        post.setPostId((long) posts.size() + 1);
        posts.add(post);
        return BlogDTO.toPostResponse(post);
    }

    @Override
    public BlogDTO.PostResponse updatePost(Long id, BlogDTO.PostRequest postRequest) {
        for (Post post : posts) {
            if (post.getPostId().equals(id)) {
               post.setTitle(postRequest.getTitle());
               post.setContent(postRequest.getContent());
               return BlogDTO.toPostResponse(post);
            }
        }
        throw new RuntimeException("Post non trouvé !");
    }

    @Override
    public void deletePost(Long id) {
        boolean exists = posts.removeIf(u -> u.getPostId().equals(id));
        if (!exists) throw new RuntimeException("Post non trouvé !");
    }
}
