package blog.blog_api.service;

import blog.blog_api.DTO.BlogDTO;
import blog.blog_api.model.Post;
import blog.blog_api.model.Tag;
import blog.blog_api.repository.PostRepository;
import blog.blog_api.repository.TagRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public TagServiceImpl(TagRepository tagRepository, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<BlogDTO.TagResponse> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(BlogDTO::toTagResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BlogDTO.TagResponse createTag(BlogDTO.TagRequest request) {
        Tag tag = BlogDTO.toTagModel(request);
        return BlogDTO.toTagResponse(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void addTagToPost(Long postId, Long tagId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post non trouvé !"));
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé !"));
        post.getTags().add(tag);
        postRepository.save(post);
    }
}
