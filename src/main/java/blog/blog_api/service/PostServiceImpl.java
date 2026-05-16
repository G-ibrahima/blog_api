package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Post;
import blog.blog_api.repository.PostRepository;
import blog.blog_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository; // base de données

    public PostServiceImpl(PostRepository userRepository) {
        this.postRepository = userRepository;
    }

    @Override
    public List<BlogDTO.PostResponse> getAllPosts() {
        return postRepository.findAll().stream().
                map(BlogDTO::toPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post non trouvé !"));

        return BlogDTO.toPostResponse(post);
    }

    @Override
    public BlogDTO.PostResponse createPost(BlogDTO.PostRequest postRequest) {
        Post post = BlogDTO.toPostModel(postRequest);
        return BlogDTO.toPostResponse(postRepository.save(post));
    }

    @Override
    public BlogDTO.PostResponse updatePost(Long id, BlogDTO.PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User non trouvé !"));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return BlogDTO.toPostResponse(postRepository.save(post));
    }

    @Override
    public void deletePost(Long id) {
        if(!postRepository.existsById(id)){
            throw new RuntimeException("Post non trouvé !");
        }
        postRepository.deleteById(id);
    }
}
