package blog.blog_api.specification;

import blog.blog_api.model.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {

    public static Specification<Post> hasUserId(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("user").get("userId"), userId);
    }

    public static Specification<Post> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Post> hasContent(String content) {
        return (root, query, cb) ->
                content == null ? null : cb.like(root.get("content"), "%" + content + "%");
    }
}