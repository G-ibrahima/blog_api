package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Post;
import blog.blog_api.model.User;
import blog.blog_api.repository.PostRepository;
import blog.blog_api.repository.UserRepository;
import blog.blog_api.specification.PostSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository; // base de données
    private final UserRepository userRepository;


    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BlogDTO.PostResponse> getAllPosts(Long userId,String title, String content) {
        Specification<Post> spec = Specification
                .where(PostSpecification.hasUserId(userId))
                .and(PostSpecification.hasTitle(title))
                .and(PostSpecification.hasContent(content));


        return postRepository.findAll(spec)
                .stream()
                .map(BlogDTO::toPostResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BlogDTO.PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post non trouvé !"));

        return BlogDTO.toPostResponse(post);
    }

    @Override
    @Transactional
    public BlogDTO.PostResponse createPost(BlogDTO.PostRequest request) {
        // 1. Trouve le User en BD
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User non trouvé !"));

        // 2. Crée le Post avec le User
        Post post = BlogDTO.toPostModel(request);
        post.setUser(user); // associe le User au Post

        // 3. Sauvegarde
        return BlogDTO.toPostResponse(postRepository.save(post));
    }

    @Override
    @Transactional
    public BlogDTO.PostResponse updatePost(Long id, BlogDTO.PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("User non trouvé !"));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return BlogDTO.toPostResponse(postRepository.save(post));
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if(!postRepository.existsById(id)){
            throw new RuntimeException("Post non trouvé !");
        }
        postRepository.deleteById(id);
    }
}
